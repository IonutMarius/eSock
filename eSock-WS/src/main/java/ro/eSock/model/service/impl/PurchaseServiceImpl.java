package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.converter.PurchaseConverter;
import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.repository.PurchaseRepository;
import ro.esock.model.service.PurchaseService;

@Service
public class PurchaseServiceImpl extends GenericServiceImpl<PurchaseDTO, Purchase, Long> implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private PurchaseConverter purchaseConverter;

	@Override
	protected PurchaseRepository getRepository() {
		return this.purchaseRepository;
	}

	@Override
	protected PurchaseConverter getEntityConverter() {
		return this.purchaseConverter;
	}
}
