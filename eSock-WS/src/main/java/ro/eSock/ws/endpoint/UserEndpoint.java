package ro.esock.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.entitiy.UserEntity;
import ro.esock.model.entitiy.UserProfileEntity;
import ro.esock.model.service.UserService;
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
		
		UserProfileEntity userProfile = new UserProfileEntity();
		userProfile.setName(userProfileXml.getName());
		userProfile.setSurname(userProfileXml.getSurname());
		userProfile.setPhoneNumber(userProfileXml.getPhoneNumber());
		userProfile.setEmailAddress(userProfileXml.getEmailAddress());
		
		UserEntity user = new UserEntity();
		user.setUsername(userXml.getUsername());
		user.setPassword(userXml.getPassword());
		user.setUserProfile(userProfile);
		
		userService.create(user);
		return response;
	}
} 
