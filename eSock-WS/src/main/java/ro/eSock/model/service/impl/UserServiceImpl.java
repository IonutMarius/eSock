package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.converter.UserConverter;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.User;
import ro.esock.model.repository.UserRepository;
import ro.esock.model.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserDTO, User, Long> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	protected UserRepository getRepository() {
		return userRepository;
	}

	@Override
	protected UserConverter getEntityConverter() {
		return this.userConverter;
	}

	@Override
	public UserDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return userConverter.toDto(user);
	}

	@Override
	public UserDTO findByUsernameAndPassword(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return userConverter.toDto(user);
	}

}
