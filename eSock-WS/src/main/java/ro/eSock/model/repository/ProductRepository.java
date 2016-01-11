package ro.esock.model.repository;

import java.util.List;

import ro.esock.model.entitiy.Product;
import ro.esock.model.filter.SearchProductFilter;

public interface ProductRepository extends GenericRepository<Product, Long> {

	Product findMatching(Product entity);
	List<Product> findByFilter(SearchProductFilter filter);

}
