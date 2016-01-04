package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.converter.UserProfileConverter;
import ro.esock.model.dto.UserProfileDTO;
import ro.esock.model.entitiy.UserProfile;
import ro.esock.model.repository.UserProfileRepository;
import ro.esock.model.service.UserProfileService;

@Service
public class UserProfileServiceImpl extends GenericServiceImpl<UserProfileDTO, UserProfile, Long> implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserProfileConverter userProfileConverter;

	@Override
	protected UserProfileRepository getRepository() {
		return this.userProfileRepository;
	}

	@Override
	protected UserProfileConverter getEntityConverter() {
		return this.userProfileConverter;
	}

}
