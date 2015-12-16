package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.persistance.entitiy.UserProfile;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.UserProfileRepository;
import ro.esock.model.persistance.service.UserProfileService;

public class UserProfileServiceImpl extends GenericServiceImpl<UserProfile, Long> implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	protected GenericRepository<UserProfile, Long> getRepository() {
		return this.userProfileRepository;
	}
}
