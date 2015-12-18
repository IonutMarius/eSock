package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.Order;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.OrderRepository;
import ro.esock.model.service.OrderService;

public class OrderServiceImpl extends GenericServiceImpl<Order, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	protected GenericRepository<Order, Long> getRepository() {
		return this.orderRepository;
	}
	
	
}
