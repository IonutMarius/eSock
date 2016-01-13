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
import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.exception.IncorrectAddressException;
import ro.esock.model.exception.ProductOutOfStockException;
import ro.esock.model.filter.SearchProductFilter;
import ro.esock.model.service.ProductService;
import ro.esock.model.service.UserService;
import ro.esock.ws.exception.NoProductFoundSoapException;
import ro.esock.ws.exception.ProductOutOfStockSoapException;
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
	
	@Autowired
	private UserService userService;

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
			e.printStackTrace();
		} catch (ProductOutOfStockException e) {
			throw new ProductOutOfStockSoapException("Insufficient stock for " + e.getProduct().getName());
		}
		
		Double total = new Double(0);
		for(PurchaseDTO purchase : orderDto.getPurchases()){
			total += purchase.getProduct().getPrice() * purchase.getQuantity();
		}
		response.setTotal(total);
		
		return response;
	}
}
