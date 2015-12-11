package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.Order;
import ro.esock.model.persistance.repository.OrderRepository;

@Repository
public class OrderRepositoryJpaImpl extends GenericRepositoryJpaImpl<Order, Long> implements OrderRepository{

}
