package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.persistance.entitiy.Purchase;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.repository.PurchaseRepository;
import ro.esock.model.persistance.service.PurchaseService;

public class PurchaseServiceImpl extends GenericServiceImpl<Purchase, Long> implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	protected GenericRepository<Purchase, Long> getRepository() {
		return this.purchaseRepository;
	}
}
