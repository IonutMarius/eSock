package ro.esock.model.persistance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.persistance.entitiy.Address;
import ro.esock.model.persistance.repository.AddressRepository;
import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.service.AddressService;

public class AddressServiceImpl extends GenericServiceImpl<Address, Long> implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Override
	protected GenericRepository<Address, Long> getRepository() {
		return this.addressRepository;
	}
	
	
}
