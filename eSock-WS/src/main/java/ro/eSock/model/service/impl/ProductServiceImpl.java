package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.ProductEntity;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.ProductRepository;
import ro.esock.model.service.ProductService;

public class ProductServiceImpl extends GenericServiceImpl<ProductEntity, Long> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	protected GenericRepository<ProductEntity, Long> getRepository() {
		return this.productRepository;
	}
}
