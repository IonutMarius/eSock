package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.UserRepository;
import ro.esock.model.persistance.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	protected GenericRepository<User, Long> getRepository() {
		return userRepository;
	}
	
}
