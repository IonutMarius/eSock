package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.UserProfileEntity;
import ro.esock.model.repository.UserProfileRepository;

@Repository
public class UserProfileRepositoryJpaImpl extends GenericRepositoryJpaImpl<UserProfileEntity, Long> implements UserProfileRepository{

}
