package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserRepositoryJpaImplTest{

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void saveAndFindUserTest(){
		User expectedUser = TestUtils.createUser("1");
		userRepository.create(expectedUser);	
		User actualUser = userRepository.findById(expectedUser.getUserId());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndDeleteUserTest(){
		User user = userRepository.create(TestUtils.createUser("1"));
		userRepository.remove(user);
		user = userRepository.findById(user.getUserId());
		
		Assert.assertEquals(null, user);
	}
	
	@Test
	public void updateAndFindUserTest(){
		User expectedUser = userRepository.create(TestUtils.createUser("1"));
		expectedUser.setUsername("u0");
		userRepository.update(expectedUser);
		User actualUser = userRepository.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}
}
