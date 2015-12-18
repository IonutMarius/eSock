package ro.esock.model.persistance.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.UserEntity;
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
		UserEntity expectedUser = TestUtils.createUser("1");
		userService.create(expectedUser);	
		UserEntity actualUser = userService.findById(expectedUser.getUserId());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndDeleteUserTest(){
		UserEntity user = userService.create(TestUtils.createUser("1"));
		userService.remove(user);
		user = userService.findById(user.getUserId());
		
		Assert.assertEquals(null, user);
	}
	
	@Test
	public void updateAndFindUserTest(){
		UserEntity expectedUser = userService.create(TestUtils.createUser("1"));
		expectedUser.setUsername("u0");
		userService.update(expectedUser);
		UserEntity actualUser = userService.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}
}
