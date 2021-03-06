package ro.esock.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.esock.domain.domain.OrderDTO;
import ro.esock.domain.domain.PurchaseDTO;
import ro.esock.domain.domain.UserDTO;
import ro.esock.model.entitiy.Order;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.entitiy.User;

@Component
public class UserConverter implements GenericEntityConverter<UserDTO, User> {

	@Autowired
	private OrderConverter orderConverter;
	@Autowired
	private UserProfileConverter userProfileConverter;
	
	@Override
	public UserDTO toDto(User entity) {
		UserDTO dto = null;
		if(entity != null){
			dto = new UserDTO();
			for(Order order : entity.getOrders()){
				OrderDTO orderDto = orderConverter.toDto(order);
				for(PurchaseDTO purchaseDto : orderDto.getPurchases()){
					purchaseDto.setUser(dto);
				}
				dto.getOrders().add(orderDto);
			}
			dto.setPassword(entity.getPassword());
			dto.setUserId(entity.getUserId());
			dto.setUsername(entity.getUsername());
			dto.setUserProfile(userProfileConverter.toDto(entity.getUserProfile()));
		}
		
		return dto;
	}

	@Override
	public User toEntity(UserDTO dto) {
		User entity = null;
		if(dto != null){
			entity = new User();
			for(OrderDTO order : dto.getOrders()){
				Order orderEnt = orderConverter.toEntity(order);
				for(Purchase purchase : orderEnt.getPurchases()){
					purchase.setUser(entity);
				}
				orderEnt.setUser(entity);
				entity.getOrders().add(orderEnt);
			}
			entity.setPassword(dto.getPassword());
			entity.setUserId(dto.getUserId());
			entity.setUsername(dto.getUsername());
			entity.setUserProfile(userProfileConverter.toEntity(dto.getUserProfile()));
		}
		
		return entity;
	}

}
