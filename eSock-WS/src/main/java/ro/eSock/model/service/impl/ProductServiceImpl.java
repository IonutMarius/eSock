package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.entitiy.Product;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.ProductRepository;
import ro.esock.model.service.ProductService;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	protected GenericRepository<Product, Long> getRepository() {
		return this.productRepository;
	}
}
