package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.repository.UserRepository;

@Repository
public class UserRepositoryJpaImpl extends AbstractGenericRepositoryJpaImpl<User, Long> implements UserRepository {

}
