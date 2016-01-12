package ro.esock.model.persistance.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class UserRepositoryJpaImplTest {

	@Autowired
	private UserRepository userRepository;

	private static final Long DEFAULT_ID = new Long(0);
	private static final String DEFAULT_USERNAME = "user0";
	private static final String Default_PASSWORD = "pass0";

	@Test
	public void createUser() {
		User user = TestUtils.createUser("_1");
		user = userRepository.create(user);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserTest() {
		User user = userRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(user);
	}

	@Test
	public void deleteUserTest() {
		User user = userRepository.findById(DEFAULT_ID);
		userRepository.remove(user);
		user = userRepository.findById(DEFAULT_ID);

		Assert.assertNull(user);
	}

	@Test
	public void updateUserTest() {
		User expectedUser = userRepository.findById(DEFAULT_ID);
		expectedUser.setUsername("u_0");
		userRepository.update(expectedUser);
		User actualUser = userRepository.findById(expectedUser.getUserId());

		Assert.assertEquals(expectedUser, actualUser);
	}

	@Test
	public void findUserByUsernameTest() {
		User user = userRepository.findByUsername(DEFAULT_USERNAME);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameFailTest() {
		User user = userRepository.findByUsername(DEFAULT_USERNAME + "_0");

		Assert.assertNull(user);
	}

	@Test
	public void saveAndFindUserByUsernameAndPasswordTest() {
		User user = userRepository.findByUsernameAndPassword(DEFAULT_USERNAME, Default_PASSWORD);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameAndPasswordFailTest() {
		User user = userRepository.findByUsernameAndPassword(DEFAULT_USERNAME + "_0", Default_PASSWORD + "_0");

		Assert.assertNull(user);
	}
}
