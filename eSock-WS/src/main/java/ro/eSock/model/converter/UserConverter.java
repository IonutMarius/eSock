package ro.esock.model.converter;

import org.springframework.stereotype.Component;

import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.User;

@Component
public class UserConverter extends GenericEntityConverter<UserDTO, User> {

	@Override
	public UserDTO toDto(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User toEntity(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
