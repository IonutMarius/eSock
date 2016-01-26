package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.domain.converter.UserConverter;
import ro.esock.domain.domain.UserDTO;
import ro.esock.domain.service.UserService;
import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	private static final Long DEFAULT_ID = new Long(0);
	private static final String DEFAULT_USERNAME = "user0";
	private static final String Default_PASSWORD = "pass0";

	@Test
	public void createUser() {
		User entity = TestUtils.createUser("_1");
		UserDTO user = userConverter.toDto(entity);
		user = userService.create(user);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserTest() {
		UserDTO user = userService.findById(DEFAULT_ID);

		Assert.assertNotNull(user);
	}

	@Test
	public void deleteUserTest() {
		UserDTO user = userService.findById(DEFAULT_ID);
		userService.remove(user);
		user = userService.findById(DEFAULT_ID);

		Assert.assertNull(user);
	}

	@Test
	public void updateUserTest() {
		UserDTO expectedUser = userService.findById(DEFAULT_ID);
		expectedUser.setUsername("u_0");
		userService.update(expectedUser);
		UserDTO actualUser = userService.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}

	@Test
	public void findUserByUsernameTest() {
		UserDTO user = userService.findByUsername(DEFAULT_USERNAME);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameFailTest() {
		UserDTO user = userService.findByUsername(DEFAULT_USERNAME + "_0");

		Assert.assertNull(user);
	}

	@Test
	public void saveAndFindUserByUsernameAndPasswordTest() {
		UserDTO user = userService.findByUsernameAndPassword(DEFAULT_USERNAME, Default_PASSWORD);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameAndPasswordFailTest() {
		UserDTO user = userService.findByUsernameAndPassword(DEFAULT_USERNAME + "_0", Default_PASSWORD + "_0");

		Assert.assertNull(user);
	}
}
