package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.OrderEntity;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.OrderRepository;
import ro.esock.model.service.OrderService;

public class OrderServiceImpl extends GenericServiceImpl<OrderEntity, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	protected GenericRepository<OrderEntity, Long> getRepository() {
		return this.orderRepository;
	}
	
	
}
