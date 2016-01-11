package ro.esock.model.service;

import java.util.List;

import ro.esock.model.dto.ProductDTO;
import ro.esock.model.entitiy.Product;
import ro.esock.model.filter.SearchProductFilter;

public interface ProductService extends GenericService<ProductDTO, Product, Long>{

	List<ProductDTO> findByFilter(SearchProductFilter filter);

}
