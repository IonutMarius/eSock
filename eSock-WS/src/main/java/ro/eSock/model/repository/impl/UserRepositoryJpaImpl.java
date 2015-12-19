package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.User;
import ro.esock.model.repository.UserRepository;

@Repository
public class UserRepositoryJpaImpl extends GenericRepositoryJpaImpl<User, Long> implements UserRepository {

}
