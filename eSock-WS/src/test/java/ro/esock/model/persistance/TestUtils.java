package ro.esock.model.persistance;

import ro.esock.model.entitiy.UserEntity;
import ro.esock.model.entitiy.UserProfileEntity;

public class TestUtils {

	private static final String DEFAULT_NAME = "name";
	private static final String DEFAULT_SURNAME = "surname";
	private static final String DEFAULT_USERNAME = "username";
	private static final String DEFAULT_PASSWORD = "password";
	
	public static UserProfileEntity createUserProfile(String sufix){
		UserProfileEntity userProfile = new UserProfileEntity();
		userProfile.setName(DEFAULT_NAME + sufix);
		userProfile.setSurname(DEFAULT_SURNAME + sufix);
		
		return userProfile;
	}
	
	public static UserEntity createUser(String sufix){
		UserEntity user = new UserEntity();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);
		user.setUserProfile(createUserProfile(sufix));
		
		return user;
	}
}
