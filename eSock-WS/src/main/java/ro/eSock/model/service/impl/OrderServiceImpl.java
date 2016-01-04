package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.converter.OrderConverter;
import ro.esock.model.dto.OrderDTO;
import ro.esock.model.entitiy.Order;
import ro.esock.model.repository.OrderRepository;
import ro.esock.model.service.OrderService;

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
