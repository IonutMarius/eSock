package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.repository.UserRepository;
import ro.esock.model.persistance.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public void createUser(User user) {
		userRepository.create(user);
	}

}
