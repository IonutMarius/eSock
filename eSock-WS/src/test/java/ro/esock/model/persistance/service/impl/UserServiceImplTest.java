package ro.esock.model.persistance.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.entitiy.UserProfile;
import ro.esock.model.persistance.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class UserServiceImplTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		UserProfile userProfile = new UserProfile();
		userProfile.setName("n2");
		userProfile.setSurname("sn2");
		
		User user = new User();
		user.setPassword("p2");
		user.setUsername("u2");
		user.setUserProfile(userProfile);
		
		userService.create(user);
	}
}
