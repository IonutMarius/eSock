package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.UserProfileEntity;
import ro.esock.model.persistance.repository.UserProfileRepository;

@Repository
public class UserProfileRepositoryJpaImpl extends GenericRepositoryJpaImpl<UserProfileEntity, Long> implements UserProfileRepository{

}
