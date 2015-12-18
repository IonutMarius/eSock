package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.PurchaseEntity;
import ro.esock.model.persistance.repository.PurchaseRepository;

@Repository
public class PurchaseRepositoryJpaImpl extends GenericRepositoryJpaImpl<PurchaseEntity, Long> implements PurchaseRepository{

}
