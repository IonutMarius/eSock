package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.PurchaseEntity;
import ro.esock.model.repository.PurchaseRepository;

@Repository
public class PurchaseRepositoryJpaImpl extends GenericRepositoryJpaImpl<PurchaseEntity, Long> implements PurchaseRepository{

}
