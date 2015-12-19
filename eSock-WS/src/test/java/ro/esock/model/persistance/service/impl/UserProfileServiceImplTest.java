package ro.esock.model.persistance.service.impl;

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
import ro.esock.model.service.UserProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserProfileServiceImplTest {

	@Autowired
	private UserProfileService userProfileService;
	
	@Test
	public void saveAndFindUserProfileTest(){
		UserProfile expectedUserProfile = TestUtils.createUserProfile("_1");
		userProfileService.create(expectedUserProfile);	
		UserProfile actualUserProfile = userProfileService.findById(expectedUserProfile.getUserProfileId());
		
		Assert.assertEquals(expectedUserProfile, actualUserProfile);
	}
	
	@Test
	public void saveAndDeleteUserProfileTest(){
		UserProfile userProfile = userProfileService.create(TestUtils.createUserProfile("_1"));
		userProfileService.remove(userProfile);
		userProfile = userProfileService.findById(userProfile.getUserProfileId());
		
		Assert.assertEquals(null, userProfile);
	}
	
	@Test
	public void updateAndFindUserProfileTest(){
		UserProfile expectedUserProfile = userProfileService.create(TestUtils.createUserProfile("_1"));
		expectedUserProfile.setName("n_0");
		userProfileService.update(expectedUserProfile);
		UserProfile actualUser = userProfileService.findById(expectedUserProfile.getUserProfileId());

		Assert.assertEquals(expectedUserProfile, actualUser);
	}
}
