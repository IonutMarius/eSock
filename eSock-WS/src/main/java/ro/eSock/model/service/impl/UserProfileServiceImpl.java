package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.UserProfileEntity;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.UserProfileRepository;
import ro.esock.model.service.UserProfileService;

public class UserProfileServiceImpl extends GenericServiceImpl<UserProfileEntity, Long> implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	protected GenericRepository<UserProfileEntity, Long> getRepository() {
		return this.userProfileRepository;
	}
}
