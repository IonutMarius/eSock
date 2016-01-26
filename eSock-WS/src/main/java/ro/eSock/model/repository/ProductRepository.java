package ro.esock.model.repository;

import java.util.List;

import ro.esock.domain.filter.SearchProductFilter;
import ro.esock.model.entitiy.Product;

public interface ProductRepository extends GenericRepository<Product, Long> {

	Product findMatching(Product entity);
	List<Product> findByFilter(SearchProductFilter filter);

}
