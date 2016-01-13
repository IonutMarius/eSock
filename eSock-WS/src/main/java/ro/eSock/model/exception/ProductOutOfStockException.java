package ro.esock.model.exception;

import ro.esock.model.dto.ProductDTO;

public class ProductOutOfStockException extends RuntimeException {

	private static final long serialVersionUID = 5127701882951890193L;

	private ProductDTO product;
	
	public ProductOutOfStockException(ProductDTO product){
		super();
		this.product = product;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
}
