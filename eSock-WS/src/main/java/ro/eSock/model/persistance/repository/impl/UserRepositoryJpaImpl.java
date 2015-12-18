package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.UserEntity;
import ro.esock.model.persistance.repository.UserRepository;

@Repository
public class UserRepositoryJpaImpl extends GenericRepositoryJpaImpl<UserEntity, Long> implements UserRepository {

}
