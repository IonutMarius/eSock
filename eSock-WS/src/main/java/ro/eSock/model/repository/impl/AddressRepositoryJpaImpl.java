package ro.esock.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.esock.model.entitiy.Address;
import ro.esock.model.repository.AddressRepository;

@Repository
public class AddressRepositoryJpaImpl extends GenericRepositoryJpaImpl<Address, Long> implements AddressRepository{

	@Override
	public Address findById(Address entity) {
		return this.findById(entity.getAddressId());
	}

}
