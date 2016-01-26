package ro.esock.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.domain.converter.UserProfileConverter;
import ro.esock.domain.domain.UserProfileDTO;
import ro.esock.domain.service.UserProfileService;
import ro.esock.model.entitiy.UserProfile;
import ro.esock.model.repository.UserProfileRepository;

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
