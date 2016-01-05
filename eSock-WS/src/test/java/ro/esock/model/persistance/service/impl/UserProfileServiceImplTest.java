package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.converter.UserProfileConverter;
import ro.esock.model.dto.UserProfileDTO;
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
	
	@Autowired
	private UserProfileConverter userProfileConverter;
	
	@Test
	public void saveAndFindUserProfileTest(){
		UserProfile entity = TestUtils.createUserProfile("_1");
		UserProfileDTO expectedUserProfile = userProfileConverter.toDto(entity);
		expectedUserProfile = userProfileService.create(expectedUserProfile);	
		UserProfileDTO actualUserProfile = userProfileService.findById(expectedUserProfile.getUserProfileId());
		
		Assert.assertEquals(expectedUserProfile, actualUserProfile);
	}
	
	@Test
	public void saveAndDeleteUserProfileTest(){
		UserProfile entity = TestUtils.createUserProfile("_1");
		UserProfileDTO userProfile = userProfileService.create(userProfileConverter.toDto(entity));
		userProfileService.remove(userProfile);
		userProfile = userProfileService.findById(userProfile.getUserProfileId());
		
		Assert.assertEquals(null, userProfile);
	}
	
	@Test
	public void updateAndFindUserProfileTest(){
		UserProfile entity = TestUtils.createUserProfile("_1");
		UserProfileDTO expectedUserProfile = userProfileService.create(userProfileConverter.toDto(entity));
		expectedUserProfile.setName("n_0");
		userProfileService.update(expectedUserProfile);
		UserProfileDTO actualUserProfile = userProfileService.findById(expectedUserProfile.getUserProfileId());

		Assert.assertEquals(expectedUserProfile, actualUserProfile);
	}
}
