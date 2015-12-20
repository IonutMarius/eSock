package ro.esock.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.service.UserService;
import ro.esock.ws.exception.PasswordsDoNotMatchException;
import ro.esock.ws.soap.user.RegisterRequest;
import ro.esock.ws.soap.user.RegisterResponse;
import ro.esock.ws.soap.user.UserXml;
import ro.esock.ws.util.ConverterUtils;

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
		if(!userXml.getPassword().equals(userXml.getPasswordConf())){
			throw new PasswordsDoNotMatchException();
		}
		userService.register(ConverterUtils.convertUserXmlToUser(userXml));
		
		return response;
	}
} 
