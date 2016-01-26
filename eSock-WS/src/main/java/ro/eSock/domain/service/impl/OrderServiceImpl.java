package ro.esock.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.domain.converter.OrderConverter;
import ro.esock.domain.domain.OrderDTO;
import ro.esock.domain.service.OrderService;
import ro.esock.model.entitiy.Order;
import ro.esock.model.repository.OrderRepository;

@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderDTO, Order, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Override
	protected OrderRepository getRepository() {
		return this.orderRepository;
	}

	@Override
	protected OrderConverter getEntityConverter() {
		return this.orderConverter;
	}

}
