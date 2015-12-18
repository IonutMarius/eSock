package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.UserProfile;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.UserProfileRepository;
import ro.esock.model.service.UserProfileService;

public class UserProfileServiceImpl extends GenericServiceImpl<UserProfile, Long> implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	protected GenericRepository<UserProfile, Long> getRepository() {
		return this.userProfileRepository;
	}
}
