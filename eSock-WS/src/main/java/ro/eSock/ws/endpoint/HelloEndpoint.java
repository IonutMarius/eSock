package ro.esock.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.service.UserService;
import ro.esock.ws.resource.HelloRequest;
import ro.esock.ws.resource.HelloResponse;

@Endpoint
public class HelloEndpoint {
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/resource";
	
	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI ,localPart = "helloRequest")
	@ResponsePayload
	public HelloResponse getCountry(@RequestPayload HelloRequest request) {
		HelloResponse response = new HelloResponse();
		User user = new User();
		user.setUsername("user1");
		user.setPassword("pass1");
		user.setUserProfileId(new Long(1));
		userService.createUser(user);
		return response;
	}
} 
