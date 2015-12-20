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
	public User register(User user) {
		User createdUser = null;
		try {
			createdUser = create(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createdUser;
	}

}
