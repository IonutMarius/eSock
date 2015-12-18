package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.ProductEntity;
import ro.esock.model.repository.ProductRepository;

@Repository
public class ProductRepositoryJpaImpl extends GenericRepositoryJpaImpl<ProductEntity, Long> implements ProductRepository{

}
