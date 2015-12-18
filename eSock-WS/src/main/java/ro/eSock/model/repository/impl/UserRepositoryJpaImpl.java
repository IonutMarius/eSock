package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.UserEntity;
import ro.esock.model.repository.UserRepository;

@Repository
public class UserRepositoryJpaImpl extends GenericRepositoryJpaImpl<UserEntity, Long> implements UserRepository {

}
