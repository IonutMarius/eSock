package ro.esock.ws.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.ws.resources.HelloRequest;
import ro.esock.ws.resources.HelloResponse;
import ro.esock.ws.service.HelloService;

@Endpoint
public class HelloEndpoint {
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/resources";
	@Autowired
	private HelloService helloService;	
	
	@PayloadRoot(namespace = NAMESPACE_URI ,localPart = "helloRequest")
	@ResponsePayload
	public HelloResponse getCountry(@RequestPayload HelloRequest request) {
		HelloResponse response = new HelloResponse();
		response.setGreeting("Hello, " + helloService.getName(request.getId()));
		return response;
	}
} 
