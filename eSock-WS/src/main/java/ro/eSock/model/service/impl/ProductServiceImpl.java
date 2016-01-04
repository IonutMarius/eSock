package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.converter.ProductConverter;
import ro.esock.model.dto.ProductDTO;
import ro.esock.model.entitiy.Product;
import ro.esock.model.repository.ProductRepository;
import ro.esock.model.service.ProductService;

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
}
