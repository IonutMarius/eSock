package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.persistance.entitiy.Order;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.OrderRepository;
import ro.esock.model.persistance.service.OrderService;

public class OrderServiceImpl extends GenericServiceImpl<Order, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	protected GenericRepository<Order, Long> getRepository() {
		return this.orderRepository;
	}
	
	
}
