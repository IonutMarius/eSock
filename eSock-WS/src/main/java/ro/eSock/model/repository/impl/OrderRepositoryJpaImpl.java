package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.OrderEntity;
import ro.esock.model.repository.OrderRepository;

@Repository
public class OrderRepositoryJpaImpl extends GenericRepositoryJpaImpl<OrderEntity, Long> implements OrderRepository{

}
