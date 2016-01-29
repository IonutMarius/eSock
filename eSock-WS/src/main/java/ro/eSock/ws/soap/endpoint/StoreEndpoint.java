package ro.esock.ws.soap.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ro.esock.domain.domain.OrderDTO;
import ro.esock.domain.domain.ProductDTO;
import ro.esock.domain.domain.PurchaseDTO;
import ro.esock.domain.exception.IncorrectAddressException;
import ro.esock.domain.exception.ProductOutOfStockException;
import ro.esock.domain.filter.SearchProductFilter;
import ro.esock.domain.service.ProductService;
import ro.esock.domain.service.UserService;
import ro.esock.ws.soap.exception.IncorrectAddressExceptionSoapException;
import ro.esock.ws.soap.exception.NoProductFoundSoapException;
import ro.esock.ws.soap.exception.ProductOutOfStockSoapException;
import ro.esock.ws.soap.store.CheckoutRequest;
import ro.esock.ws.soap.store.CheckoutResponse;
import ro.esock.ws.soap.store.GetProductRequest;
import ro.esock.ws.soap.store.GetProductResponse;
import ro.esock.ws.soap.store.ProductXml;
import ro.esock.ws.soap.store.RegisterProductsRequest;
import ro.esock.ws.soap.store.RegisterProductsResponse;
import ro.esock.ws.soap.store.SearchProductsRequest;
import ro.esock.ws.soap.store.SearchProductsResponse;
import ro.esock.ws.soap.util.ConverterUtils;

@Endpoint
public class StoreEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreEndpoint.class);
	private static final String NAMESPACE_URI = "http://eSock.ro/ws/soap/store";

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerProductsRequest")
	@ResponsePayload
	public RegisterProductsResponse registerProduct(@RequestPayload RegisterProductsRequest request) {
		for (ProductXml productXml : request.getProduct()) {
			LOGGER.info("Registering product: " + productXml.getName() + "(" + productXml.getPrice() + "$) x "
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
			throw new NoProductFoundSoapException();
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkoutRequest")
	@ResponsePayload
	public CheckoutResponse checkout(@RequestPayload CheckoutRequest request){
		CheckoutResponse response = new CheckoutResponse();
		OrderDTO orderDto = ConverterUtils.convertOrderXmlToOrderDTO(request.getOrderXml());
		try {
			orderDto = userService.addOrder(request.getOrderXml().getUserId(), orderDto);
		} catch (IncorrectAddressException e) {
			LOGGER.error("The address provided is incorrect", e);
			throw new IncorrectAddressExceptionSoapException();
		} catch (ProductOutOfStockException e) {
			String exceptionMsg = "Insufficient stock for " + e.getProduct().getName();
			LOGGER.error(exceptionMsg, e);
			throw new ProductOutOfStockSoapException(exceptionMsg);
		}
		
		Double total = new Double(0);
		for(PurchaseDTO purchase : orderDto.getPurchases()){
			total += purchase.getProduct().getPrice() * purchase.getQuantity();
		}
		response.setTotal(total);
		
		return response;
	}
}
