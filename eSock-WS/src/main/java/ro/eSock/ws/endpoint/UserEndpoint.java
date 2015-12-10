package ro.esock.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.entitiy.UserProfile;
import ro.esock.model.persistance.service.UserService;
import ro.esock.ws.soap.user.RegisterRequest;
import ro.esock.ws.soap.user.RegisterResponse;
import ro.esock.ws.soap.user.UserProfileXml;
import ro.esock.ws.soap.user.UserXml;

@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/soap/user";
	
	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI ,localPart = "registerRequest")
	@ResponsePayload
	public RegisterResponse register(@RequestPayload RegisterRequest request) {
		RegisterResponse response = new RegisterResponse();

		UserXml userXml = request.getUser();
		UserProfileXml userProfileXml = userXml.getUserProfile();
		
		UserProfile userProfile = new UserProfile();
		userProfile.setName(userProfileXml.getName());
		userProfile.setSurname(userProfileXml.getSurname());
		userProfile.setPhoneNumber(userProfileXml.getPhoneNumber());
		userProfile.setEmailAddress(userProfileXml.getEmailAddress());
		
		User user = new User();
		user.setUsername(userXml.getUsername());
		user.setPassword(userXml.getPassword());
		user.setUserProfile(userProfile);
		
		userService.createUser(user);
		return response;
	}
} 
