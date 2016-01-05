package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.Product;
import ro.esock.model.repository.ProductRepository;

@Repository
public class ProductRepositoryJpaImpl extends GenericRepositoryJpaImpl<Product, Long> implements ProductRepository{

	@Override
	public Product findById(Product entity) {
		return this.findById(entity.getProductId());
	}

}
