package ro.esock.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.esock.model.dto.OrderDTO;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.Order;
import ro.esock.model.entitiy.User;

@Component
public class UserConverter extends GenericEntityConverter<UserDTO, User> {

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
				dto.getOrders().add(orderConverter.toDto(order));
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
				entity.getOrders().add(orderConverter.toEntity(order));
			}
			entity.setPassword(dto.getPassword());
			entity.setUserId(dto.getUserId());
			entity.setUsername(dto.getUsername());
			entity.setUserProfile(userProfileConverter.toEntity(dto.getUserProfile()));
		}
		
		return entity;
	}

}
