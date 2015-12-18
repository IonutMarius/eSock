package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.PurchaseEntity;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.repository.PurchaseRepository;
import ro.esock.model.service.PurchaseService;

public class PurchaseServiceImpl extends GenericServiceImpl<PurchaseEntity, Long> implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	protected GenericRepository<PurchaseEntity, Long> getRepository() {
		return this.purchaseRepository;
	}
}
