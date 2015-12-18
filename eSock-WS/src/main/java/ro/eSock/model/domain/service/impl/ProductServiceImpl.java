package ro.esock.model.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.domain.service.ProductService;
import ro.esock.model.persistance.entitiy.ProductEntity;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.ProductRepository;

public class ProductServiceImpl extends GenericServiceImpl<ProductEntity, Long> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	protected GenericRepository<ProductEntity, Long> getRepository() {
		return this.productRepository;
	}
}
