package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.entitiy.UserEntity;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.UserRepository;
import ro.esock.model.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserEntity, Long> implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	protected GenericRepository<UserEntity, Long> getRepository() {
		return userRepository;
	}
	
}
