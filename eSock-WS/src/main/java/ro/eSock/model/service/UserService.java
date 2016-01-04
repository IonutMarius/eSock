package ro.esock.model.service;

import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.User;

public interface UserService extends GenericService<UserDTO, User, Long>{

	UserDTO findByUsername(String username);
	UserDTO findByUsernameAndPassword(String username, String password);
}
