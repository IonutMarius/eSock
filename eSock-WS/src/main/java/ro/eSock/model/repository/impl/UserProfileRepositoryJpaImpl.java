package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.UserProfile;
import ro.esock.model.repository.UserProfileRepository;

@Repository
public class UserProfileRepositoryJpaImpl extends GenericRepositoryJpaImpl<UserProfile, Long> implements UserProfileRepository{

	@Override
	public UserProfile findById(UserProfile entity) {
		return findById(entity.getUserProfileId());
	}

}
