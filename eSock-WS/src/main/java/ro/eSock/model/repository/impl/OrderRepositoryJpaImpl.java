package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.Order;
import ro.esock.model.repository.OrderRepository;

@Repository
public class OrderRepositoryJpaImpl extends GenericRepositoryJpaImpl<Order, Long> implements OrderRepository{

}
