package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.Address;
import ro.esock.model.repository.AddressRepository;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.service.AddressService;

public class AddressServiceImpl extends GenericServiceImpl<Address, Long> implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Override
	protected GenericRepository<Address, Long> getRepository() {
		return this.addressRepository;
	}
	
	
}
