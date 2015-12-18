package ro.esock.model.persistance.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.persistance.entitiy.AddressEntity;
import ro.esock.model.persistance.repository.AddressRepository;

@Repository
public class AddressRepositoryJpaImpl extends GenericRepositoryJpaImpl<AddressEntity, Long> implements AddressRepository{

}
