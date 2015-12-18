package ro.esock.model.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.domain.service.UserService;
import ro.esock.model.persistance.entitiy.UserEntity;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.UserRepository;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserEntity, Long> implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	protected GenericRepository<UserEntity, Long> getRepository() {
		return userRepository;
	}
	
}
