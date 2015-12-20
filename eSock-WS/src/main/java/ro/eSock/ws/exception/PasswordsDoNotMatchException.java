package ro.esock.ws.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "The password and the password confirmation do not match")
public class PasswordsDoNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 3233978297759256092L;

}
