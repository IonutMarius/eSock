package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.entitiy.User;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.UserRepository;
import ro.esock.model.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected GenericRepository<User, Long> getRepository() {
		return userRepository;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
