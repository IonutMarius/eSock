package ro.esock.model.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.domain.service.PurchaseService;
import ro.esock.model.persistance.entitiy.PurchaseEntity;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.PurchaseRepository;

public class PurchaseServiceImpl extends GenericServiceImpl<PurchaseEntity, Long> implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	protected GenericRepository<PurchaseEntity, Long> getRepository() {
		return this.purchaseRepository;
	}
}
