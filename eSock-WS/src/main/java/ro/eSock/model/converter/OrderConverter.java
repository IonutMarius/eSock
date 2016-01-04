package ro.esock.model.converter;

import org.springframework.stereotype.Component;

import ro.esock.model.dto.OrderDTO;
import ro.esock.model.entitiy.Order;


@Component
public class OrderConverter extends GenericEntityConverter<OrderDTO, Order> {

	@Override
	public OrderDTO toDto(Order entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order toEntity(OrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
