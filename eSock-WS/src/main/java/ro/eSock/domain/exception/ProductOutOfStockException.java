package ro.esock.domain.exception;

import ro.esock.domain.domain.ProductDTO;

public class ProductOutOfStockException extends RuntimeException {

	private static final long serialVersionUID = 5127701882951890193L;

	private final ProductDTO product;
	
	public ProductOutOfStockException(ProductDTO product){
		super();
		this.product = product;
	}

	public ProductDTO getProduct() {
		return product;
	}
}
