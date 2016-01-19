package ro.esock.ws.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.dto.UserDTO;
import ro.esock.model.service.UserService;
import ro.esock.ws.exception.LoginFailedSoapException;
import ro.esock.ws.exception.PasswordsDoNotMatchSoapException;
import ro.esock.ws.exception.UserAlreadyExistsSoapException;
import ro.esock.ws.soap.user.Credentials;
import ro.esock.ws.soap.user.LoginRequest;
import ro.esock.ws.soap.user.LoginResponse;
import ro.esock.ws.soap.user.RegisterRequest;
import ro.esock.ws.soap.user.RegisterResponse;
import ro.esock.ws.soap.user.UserXml;
import ro.esock.ws.util.ConverterUtils;

@Endpoint
public class UserEndpoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserEndpoint.class);
	
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/soap/user";
	
	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI ,localPart = "registerRequest")
	@ResponsePayload
	public RegisterResponse register(@RequestPayload RegisterRequest request) {
		RegisterResponse response = new RegisterResponse();

		UserXml userXml = request.getUser();
		if(!userXml.getPassword().equals(userXml.getPasswordConf())){
			LOGGER.error("Password '" + userXml.getPassword() + "' do not match '" + userXml.getPasswordConf() + "'");
			throw new PasswordsDoNotMatchSoapException();
		}
		
		UserDTO user = null;
		try {
			user = userService.create(ConverterUtils.convertUserDTOXmlToUser(userXml));
		} catch (DataIntegrityViolationException e) {
			LOGGER.error("Constraint violated - user already exists", e);
			throw new UserAlreadyExistsSoapException();
		}
		
		response.setUser(ConverterUtils.convertUserDTOToUserXml(user));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI ,localPart = "loginRequest")
	@ResponsePayload
	public LoginResponse login(@RequestPayload LoginRequest request) {
		LoginResponse response = new LoginResponse();
		
		Credentials credentials = request.getCredentials();
		UserDTO user = userService.findByUsername(credentials.getUsername());
		
		if(user == null){
			LOGGER.error("The username or password is incorrect");
			throw new LoginFailedSoapException();
		}
		
		response.setUser(ConverterUtils.convertUserDTOToUserXml(user));
		return response;
	}
} 
