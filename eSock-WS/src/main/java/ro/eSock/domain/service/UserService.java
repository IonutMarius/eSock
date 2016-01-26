package ro.esock.domain.service;

import ro.esock.domain.domain.OrderDTO;
import ro.esock.domain.domain.UserDTO;
import ro.esock.model.entitiy.User;

public interface UserService extends GenericService<UserDTO, User, Long>{

	UserDTO findByUsername(String username);
	UserDTO findByUsernameAndPassword(String username, String password);
	OrderDTO addOrder(Long userId, OrderDTO orderDto);
}
