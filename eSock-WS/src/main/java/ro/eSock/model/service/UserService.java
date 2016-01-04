package ro.esock.model.service;

import ro.esock.model.entitiy.User;

public interface UserService extends GenericService<User, Long>{

	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
