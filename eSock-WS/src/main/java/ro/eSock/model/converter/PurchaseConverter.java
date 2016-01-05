package ro.esock.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.entitiy.Purchase;

@Component
public class PurchaseConverter extends GenericEntityConverter<PurchaseDTO, Purchase> {

	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public PurchaseDTO toDto(Purchase entity) {
		PurchaseDTO dto = null;
		if(entity != null){
			dto = new PurchaseDTO();
			dto.setProduct(productConverter.toDto(entity.getProduct()));
			dto.setPurchaseId(entity.getPurchaseId());
			dto.setQuantity(entity.getQuantity());
			dto.setUser(userConverter.toDto(entity.getUser()));
		}
		
		return dto;
	}

	@Override
	public Purchase toEntity(PurchaseDTO dto) {
		Purchase entity = null;
		if(dto != null){
			entity = new Purchase();
			entity.setProduct(productConverter.toEntity(dto.getProduct()));
			entity.setPurchaseId(dto.getPurchaseId());
			entity.setQuantity(dto.getQuantity());
			entity.setUser(userConverter.toEntity(dto.getUser()));
		}
		
		return entity;
	}

}
