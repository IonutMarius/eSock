package ro.esock.model.persistance.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.UserProfile;
import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.repository.UserProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserProfileRepositoryJpaImplTest {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Test
	public void saveAndFindUserProfileTest(){
		UserProfile expectedUserProfile = TestUtils.createUserProfile("_1");
		userProfileRepository.create(expectedUserProfile);	
		UserProfile actualUserProfile = userProfileRepository.findById(expectedUserProfile.getUserProfileId());
		
		Assert.assertEquals(expectedUserProfile, actualUserProfile);
	}
	
	@Test
	public void saveAndDeleteUserProfileTest(){
		UserProfile userProfile = userProfileRepository.create(TestUtils.createUserProfile("_1"));
		userProfileRepository.remove(userProfile);
		userProfile = userProfileRepository.findById(userProfile.getUserProfileId());
		
		Assert.assertEquals(null, userProfile);
	}
	
	@Test
	public void updateAndFindUserProfileTest(){
		UserProfile expectedUserProfile = userProfileRepository.create(TestUtils.createUserProfile("_1"));
		expectedUserProfile.setName("n_0");
		userProfileRepository.update(expectedUserProfile);
		UserProfile actualUser = userProfileRepository.findById(expectedUserProfile.getUserProfileId());

		Assert.assertEquals(expectedUserProfile, actualUser);
	}
}
