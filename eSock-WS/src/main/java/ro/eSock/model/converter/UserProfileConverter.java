package ro.esock.model.converter;

import org.springframework.stereotype.Component;

import ro.esock.model.dto.UserProfileDTO;
import ro.esock.model.entitiy.UserProfile;

@Component
public class UserProfileConverter extends GenericEntityConverter<UserProfileDTO, UserProfile> {

	@Override
	public UserProfileDTO toDto(UserProfile entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile toEntity(UserProfileDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
