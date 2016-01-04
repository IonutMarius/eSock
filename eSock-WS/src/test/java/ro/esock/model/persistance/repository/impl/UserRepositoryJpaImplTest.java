package ro.esock.model.persistance.repository.impl;

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
import ro.esock.model.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserRepositoryJpaImplTest{

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void saveAndFindUserTest(){
		User expectedUser = TestUtils.createUser("_1");
		userRepository.create(expectedUser);	
		User actualUser = userRepository.findById(expectedUser.getUserId());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndDeleteUserTest(){
		User user = userRepository.create(TestUtils.createUser("_1"));
		userRepository.remove(user);
		user = userRepository.findById(user.getUserId());
		
		Assert.assertEquals(null, user);
	}
	
	@Test
	public void updateAndFindUserTest(){
		User expectedUser = userRepository.create(TestUtils.createUser("_1"));
		expectedUser.setUsername("u_0");
		userRepository.update(expectedUser);
		User actualUser = userRepository.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void saveAndFindUserByUsernameTest(){
		User expectedUser = TestUtils.createUser("_1");
		userRepository.create(expectedUser);	
		User actualUser = userRepository.findByUsername(expectedUser.getUsername());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void findUserByUsernameFailTest(){
		User expectedUser = TestUtils.createUser("_1");
		User actualUser = userRepository.findByUsername(expectedUser.getUsername());
		
		Assert.assertEquals(null, actualUser);
	}
	
	@Test
	public void saveAndFindUserByUsernameAndPasswordTest(){
		User expectedUser = TestUtils.createUser("_1");
		userRepository.create(expectedUser);	
		User actualUser = userRepository.findByUsernameAndPassword(expectedUser.getUsername(), expectedUser.getPassword());
		
		Assert.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void findUserByUsernameAndPasswordFailTest(){
		User expectedUser = TestUtils.createUser("_1");
		User actualUser = userRepository.findByUsernameAndPassword(expectedUser.getUsername(), expectedUser.getPassword());
		
		Assert.assertEquals(null, actualUser);
	}
}
