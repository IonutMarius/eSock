package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.OrderEntity;
import ro.esock.model.persistance.repository.OrderRepository;

@Repository
public class OrderRepositoryJpaImpl extends GenericRepositoryJpaImpl<OrderEntity, Long> implements OrderRepository{

}
