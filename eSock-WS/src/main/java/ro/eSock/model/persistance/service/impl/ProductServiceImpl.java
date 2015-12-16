package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.persistance.entitiy.Product;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.ProductRepository;
import ro.esock.model.persistance.service.ProductService;

public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	protected GenericRepository<Product, Long> getRepository() {
		return this.productRepository;
	}
}
