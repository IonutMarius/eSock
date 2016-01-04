package ro.esock.model.repository;

import ro.esock.model.entitiy.User;

public interface UserRepository extends GenericRepository<User, Long>{

	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
