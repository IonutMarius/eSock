package ro.esock.ws.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.model.dto.OrderDTO;
import ro.esock.model.dto.ProductDTO;
import ro.esock.model.filter.SearchProductFilter;
import ro.esock.model.service.ProductService;
import ro.esock.ws.exception.NoProductFoundException;
import ro.esock.ws.soap.store.CheckoutRequest;
import ro.esock.ws.soap.store.CheckoutResponse;
import ro.esock.ws.soap.store.GetProductRequest;
import ro.esock.ws.soap.store.GetProductResponse;
import ro.esock.ws.soap.store.ProductXml;
import ro.esock.ws.soap.store.RegisterProductsRequest;
import ro.esock.ws.soap.store.RegisterProductsResponse;
import ro.esock.ws.soap.store.SearchProductsRequest;
import ro.esock.ws.soap.store.SearchProductsResponse;
import ro.esock.ws.util.ConverterUtils;

@Endpoint
public class StoreEndpoint {

	private static final Logger logger = LoggerFactory.getLogger(StoreEndpoint.class);
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/soap/store";

	@Autowired
	private ProductService productService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerProductsRequest")
	@ResponsePayload
	public RegisterProductsResponse registerProduct(@RequestPayload RegisterProductsRequest request) {
		for (ProductXml productXml : request.getProduct()) {
			logger.info("Registering product: " + productXml.getName() + "(" + productXml.getPrice() + "$) x "
					+ productXml.getQuantity());
			productService.create(ConverterUtils.convertProductXmlToProductDTO(productXml));
		}
		RegisterProductsResponse response = new RegisterProductsResponse();
		response.setMessage("Success");

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchProductsRequest")
	@ResponsePayload
	public SearchProductsResponse searchProducts(@RequestPayload SearchProductsRequest request) {
		SearchProductsResponse response = new SearchProductsResponse();
		SearchProductFilter filter = ConverterUtils
				.convertSearchProductFilterXmlToSearchProductFilter(request.getSearchProductFilter());
		List<ProductDTO> foundProducts = productService.findByFilter(filter);
		for (ProductDTO product : foundProducts) {
			response.getProduct().add(ConverterUtils.convertProductDTOToProductXml(product));
		}

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
	@ResponsePayload
	public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
		GetProductResponse response = new GetProductResponse();
		ProductDTO product = productService.findById(request.getProductId());
		if (product != null) {
			response.setProduct(ConverterUtils.convertProductDTOToProductXml(product));
		} else {
			throw new NoProductFoundException();
		}
		return response;
	}
	
	public CheckoutResponse checkout(CheckoutRequest request){
		CheckoutResponse response = new CheckoutResponse();
		OrderDTO purchaseDto = ConverterUtils.convertOrderXmlToOrderDTO(request.getOrderXml());
		
		
		return response;
	}
}
