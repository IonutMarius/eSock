package ro.esock.model.persistance.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.UserProfile;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.repository.UserProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserProfileRepositoryJpaImplTest {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	private static final Long DEFAULT_ID = new Long(0);
	
	@Test
	public void createUserProfileTest(){
		UserProfile userProfile = TestUtils.createUserProfile("_1");
		userProfile = userProfileRepository.create(userProfile);
		
		Assert.assertNotNull(userProfile);
	}
	
	@Test
	public void findUserProfileTest(){	
		UserProfile userProfile = userProfileRepository.findById(DEFAULT_ID);
		
		Assert.assertNotNull(userProfile);
	}
	
	@Test
	public void deleteUserProfileTest(){
		UserProfile userProfile = userProfileRepository.findById(DEFAULT_ID);
		userProfileRepository.remove(userProfile);
		userProfile = userProfileRepository.findById(userProfile.getUserProfileId());
		
		Assert.assertNull(userProfile);
	}
	
	@Test
	public void updateUserProfileTest(){
		UserProfile expectedUserProfile = userProfileRepository.findById(DEFAULT_ID);
		expectedUserProfile.setName("n_0");
		userProfileRepository.update(expectedUserProfile);
		UserProfile actualUser = userProfileRepository.findById(expectedUserProfile.getUserProfileId());

		Assert.assertEquals(expectedUserProfile, actualUser);
	}
}
