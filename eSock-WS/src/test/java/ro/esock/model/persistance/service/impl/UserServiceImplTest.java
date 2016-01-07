package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.converter.UserConverter;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Test
	public void saveAndFindUserTest(){
		User entity = TestUtils.createUser("1");
		UserDTO expectedUser = userConverter.toDto(entity);
		userService.create(expectedUser);	
		UserDTO actualUser = userService.findById(expectedUser.getUserId());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndDeleteUserTest(){
		User entity = TestUtils.createUser("1");
		UserDTO user = userConverter.toDto(entity);
		userService.remove(user);
		user = userService.findById(user.getUserId());
		
		Assert.assertEquals(null, user);
	}
	
	@Test
	public void updateAndFindUserTest(){
		User entity = TestUtils.createUser("1");
		UserDTO expectedUser = userConverter.toDto(entity);
		expectedUser.setUsername("u0");
		userService.update(expectedUser);
		UserDTO actualUser = userService.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndFindUserByUsernameTest(){
		User entity = TestUtils.createUser("1");
		UserDTO expectedUser = userConverter.toDto(entity);
		userService.create(expectedUser);	
		UserDTO actualUser = userService.findByUsername(expectedUser.getUsername());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void findUserByUsernameFailTest(){
		User entity = TestUtils.createUser("1");
		UserDTO expectedUser = userConverter.toDto(entity);
		UserDTO actualUser = userService.findByUsername(expectedUser.getUsername());
		
		Assert.assertEquals(null, actualUser);
	}
	
	@Test
	public void saveAndFindUserByUsernameAndPasswordTest(){
		User entity = TestUtils.createUser("1");
		UserDTO expectedUser = userConverter.toDto(entity);
		userService.create(expectedUser);	
		UserDTO actualUser = userService.findByUsernameAndPassword(expectedUser.getUsername(), expectedUser.getPassword());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void findUserByUsernameAndPasswordFailTest(){
		User entity = TestUtils.createUser("1");
		UserDTO expectedUser = userConverter.toDto(entity);
		UserDTO actualUser = userService.findByUsernameAndPassword(expectedUser.getUsername(), expectedUser.getPassword());
		
		Assert.assertEquals(null, actualUser);
	}
}
