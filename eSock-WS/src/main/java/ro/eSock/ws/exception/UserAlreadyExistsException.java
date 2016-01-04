package ro.esock.ws.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "The username already exists")
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -3847921314315594570L;

}
