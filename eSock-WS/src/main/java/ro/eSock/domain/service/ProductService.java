package ro.esock.domain.service;

import java.util.List;

import ro.esock.domain.domain.ProductDTO;
import ro.esock.domain.filter.SearchProductFilter;
import ro.esock.model.entitiy.Product;

public interface ProductService extends GenericService<ProductDTO, Product, Long>{

	List<ProductDTO> findByFilter(SearchProductFilter filter);

}
