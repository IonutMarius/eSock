package ro.esock.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.domain.converter.PurchaseConverter;
import ro.esock.domain.domain.PurchaseDTO;
import ro.esock.domain.service.PurchaseService;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.repository.PurchaseRepository;

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
