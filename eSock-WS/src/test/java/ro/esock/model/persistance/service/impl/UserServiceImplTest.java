package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void saveAndFindUserTest(){
		User expectedUser = TestUtils.createUser("1");
		userService.create(expectedUser);	
		User actualUser = userService.findById(expectedUser.getUserId());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndDeleteUserTest(){
		User user = userService.create(TestUtils.createUser("1"));
		userService.remove(user);
		user = userService.findById(user.getUserId());
		
		Assert.assertEquals(null, user);
	}
	
	@Test
	public void updateAndFindUserTest(){
		User expectedUser = userService.create(TestUtils.createUser("1"));
		expectedUser.setUsername("u0");
		userService.update(expectedUser);
		User actualUser = userService.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndFindUserByUsernameTest(){
		User expectedUser = TestUtils.createUser("_1");
		userService.create(expectedUser);	
		User actualUser = userService.findByUsername(expectedUser.getUsername());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void findUserByUsernameFailTest(){
		User expectedUser = TestUtils.createUser("_1");
		User actualUser = userService.findByUsername(expectedUser.getUsername());
		
		Assert.assertEquals(null, actualUser);
	}
	
	@Test
	public void saveAndFindUserByUsernameAndPasswordTest(){
		User expectedUser = TestUtils.createUser("_1");
		userService.create(expectedUser);	
		User actualUser = userService.findByUsernameAndPassword(expectedUser.getUsername(), expectedUser.getPassword());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void findUserByUsernameAndPasswordFailTest(){
		User expectedUser = TestUtils.createUser("_1");
		User actualUser = userService.findByUsernameAndPassword(expectedUser.getUsername(), expectedUser.getPassword());
		
		Assert.assertEquals(null, actualUser);
	}
}
