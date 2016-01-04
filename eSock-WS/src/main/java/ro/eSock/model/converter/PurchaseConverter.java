package ro.esock.model.converter;

import org.springframework.stereotype.Component;

import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.entitiy.Purchase;

@Component
public class PurchaseConverter extends GenericEntityConverter<PurchaseDTO, Purchase> {

	@Override
	public PurchaseDTO toDto(Purchase entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Purchase toEntity(PurchaseDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
