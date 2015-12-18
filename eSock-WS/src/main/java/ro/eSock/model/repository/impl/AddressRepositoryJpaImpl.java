package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.AddressEntity;
import ro.esock.model.repository.AddressRepository;

@Repository
public class AddressRepositoryJpaImpl extends GenericRepositoryJpaImpl<AddressEntity, Long> implements AddressRepository{

}
