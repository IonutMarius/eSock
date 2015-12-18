package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.ProductEntity;
import ro.esock.model.persistance.repository.ProductRepository;

@Repository
public class ProductRepositoryJpaImpl extends GenericRepositoryJpaImpl<ProductEntity, Long> implements ProductRepository{

}
