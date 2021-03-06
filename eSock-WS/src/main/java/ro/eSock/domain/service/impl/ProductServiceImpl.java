package ro.esock.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.domain.converter.ProductConverter;
import ro.esock.domain.domain.ProductDTO;
import ro.esock.domain.filter.SearchProductFilter;
import ro.esock.domain.service.ProductService;
import ro.esock.model.entitiy.Product;
import ro.esock.model.repository.ProductRepository;

@Service
public class ProductServiceImpl extends GenericServiceImpl<ProductDTO, Product, Long> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductConverter productConverter;

	@Override
	protected ProductRepository getRepository() {
		return this.productRepository;
	}

	@Override
	protected ProductConverter getEntityConverter() {
		return this.productConverter;
	}
	
	@Override
	@Transactional
	public ProductDTO create(ProductDTO productDto) {
		Product match = productRepository.findMatching(productConverter.toEntity(productDto));
		ProductDTO matchDto;
		if(match != null){
			match.setStock(match.getStock() + productDto.getStock());
			match = productRepository.update(match);
			matchDto = productConverter.toDto(match);
		}
		else{
			matchDto = super.create(productDto);
		}
		
		return matchDto;
	}

	@Override
	public List<ProductDTO> findByFilter(SearchProductFilter filter) {
		List<ProductDTO> foundProducts = new ArrayList<>();
		for(Product product : productRepository.findByFilter(filter)){
			foundProducts.add(productConverter.toDto(product));
		}
		
		return foundProducts;
	}
}
