package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.Purchase;
import ro.esock.model.repository.PurchaseRepository;

@Repository
public class PurchaseRepositoryJpaImpl extends GenericRepositoryJpaImpl<Purchase, Long> implements PurchaseRepository{

	@Override
	public Purchase findById(Purchase entity) {
		return this.findById(entity.getPurchaseId());
	}

}
