package ro.esock.model.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.domain.service.OrderService;
import ro.esock.model.persistance.entitiy.OrderEntity;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.OrderRepository;

public class OrderServiceImpl extends GenericServiceImpl<OrderEntity, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	protected GenericRepository<OrderEntity, Long> getRepository() {
		return this.orderRepository;
	}
	
	
}
