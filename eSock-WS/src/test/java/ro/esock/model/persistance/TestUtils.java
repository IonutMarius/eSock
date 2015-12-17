package ro.esock.model.persistance;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.entitiy.UserProfile;

public class TestUtils {

	private static final String DEFAULT_NAME = "name";
	private static final String DEFAULT_SURNAME = "surname";
	private static final String DEFAULT_USERNAME = "username";
	private static final String DEFAULT_PASSWORD = "password";
	
	public static UserProfile createUserProfile(String sufix){
		UserProfile userProfile = new UserProfile();
		userProfile.setName(DEFAULT_NAME + sufix);
		userProfile.setSurname(DEFAULT_SURNAME + sufix);
		
		return userProfile;
	}
	
	public static User createUser(String sufix){
		User user = new User();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);
		user.setUserProfile(createUserProfile(sufix));
		
		return user;
	}
}
