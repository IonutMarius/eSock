package ro.esock.ws.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.service.ProductService;
import ro.esock.ws.soap.product.ProductXml;
import ro.esock.ws.soap.product.RegisterProductsRequest;
import ro.esock.ws.soap.product.RegisterProductsResponse;

@Endpoint
public class ProductEndpoint {

	private static final Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/soap/product";

	@Autowired
	private ProductService productService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerProductsRequest")
	@ResponsePayload
	public RegisterProductsResponse registerProduct(@RequestPayload RegisterProductsRequest request) {
		for (ProductXml productXml : request.getProduct()) {
			logger.info("Registering product: " + productXml.getName() + "(" + productXml.getPrice() + "$) x "
					+ productXml.getQuantity());
		}
		RegisterProductsResponse response = new RegisterProductsResponse();
		response.setMessage("Success");

		return response;
	}
}
