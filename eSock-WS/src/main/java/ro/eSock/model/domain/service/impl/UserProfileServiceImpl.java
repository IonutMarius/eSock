package ro.esock.model.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.domain.service.UserProfileService;
import ro.esock.model.persistance.entitiy.UserProfileEntity;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.UserProfileRepository;

public class UserProfileServiceImpl extends GenericServiceImpl<UserProfileEntity, Long> implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	protected GenericRepository<UserProfileEntity, Long> getRepository() {
		return this.userProfileRepository;
	}
}
