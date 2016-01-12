package ro.esock.ws.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "No product with the provided id was found")
public class NoProductFoundException extends RuntimeException {

	private static final long serialVersionUID = -3847921314315594570L;

}
