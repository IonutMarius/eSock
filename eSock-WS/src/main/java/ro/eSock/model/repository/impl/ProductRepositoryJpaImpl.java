package ro.esock.model.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.Product;
import ro.esock.model.filter.SearchProductFilter;
import ro.esock.model.repository.ProductRepository;

@Repository
public class ProductRepositoryJpaImpl extends GenericRepositoryJpaImpl<Product, Long> implements ProductRepository{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryJpaImpl.class);

	@Override
	public Product findById(Product entity) {
		return this.findById(entity.getProductId());
	}

	@Override
	public Product findMatching(Product entity) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(entityClass);
		Root<Product> product = query.from(entityClass);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(product.get("name"), entity.getName()));
		predicates.add(criteriaBuilder.equal(product.get("description"), entity.getDescription()));
		predicates.add(criteriaBuilder.equal(product.<Double>get("price"), entity.getPrice()));
		
		query.select(product).where(predicates.toArray(new Predicate[predicates.size()]));
		
		Product foundProduct = null;
		try {
			foundProduct = entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			logger.error("No product was found", e);;
		}
		
		return foundProduct;
	}

	@Override
	public List<Product> findByFilter(SearchProductFilter filter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(entityClass);
		Root<Product> product = query.from(entityClass);
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getPriceMin() != null && filter.getPriceMax() != null){
			predicates.add(criteriaBuilder.between(product.<Double>get("price"), filter.getPriceMin(), filter.getPriceMax()));
		}
		else if(filter.getPriceMin() != null){
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(product.<Double>get("price"), filter.getPriceMin()));
		}
		else if(filter.getPriceMax() != null){
			predicates.add(criteriaBuilder.lessThanOrEqualTo(product.<Double>get("price"), filter.getPriceMax()));
		}
		
		if(filter.getKeywords().size() > 0){
			StringBuilder sbLike = new StringBuilder();
			sbLike.append("%");
			for(String keyword: filter.getKeywords()){
				sbLike.append(keyword);
				sbLike.append("%");
			}
			sbLike.append("%");
			
			Predicate namePredicate = criteriaBuilder.like(product.get("name"), sbLike.toString());
			Predicate descPredicate = criteriaBuilder.like(product.get("description"), sbLike.toString());
			predicates.add(criteriaBuilder.or(namePredicate, descPredicate));
		}
		
		query.select(product).where(predicates.toArray(new Predicate[predicates.size()]));
		
		List<Product> foundProducts = null;
		try {
			foundProducts = entityManager.createQuery(query).getResultList();
		} catch (NoResultException e) {
			logger.error("No product was found", e);;
		}
		
		return foundProducts;
	}

}
