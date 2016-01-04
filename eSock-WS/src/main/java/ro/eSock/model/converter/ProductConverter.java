package ro.esock.model.converter;

import org.springframework.stereotype.Component;

import ro.esock.model.dto.ProductDTO;
import ro.esock.model.entitiy.Product;

@Component
public class ProductConverter extends GenericEntityConverter<ProductDTO, Product> {

	@Override
	public ProductDTO toDto(Product entity) {
		ProductDTO productDto = new ProductDTO();
		productDto.setDescription(entity.getDescription());
		productDto.setName(entity.getName());
		productDto.setPrice(entity.getPrice());
		productDto.setProductId(entity.getProductId());
		productDto.setStock(entity.getStock());
		
		return productDto;
	}

	@Override
	public Product toEntity(ProductDTO dto) {
		Product product = new Product();
		product.setDescription(dto.getDescription());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setProductId(dto.getProductId());
		product.setStock(dto.getStock());
		
		return product;
	}

}
