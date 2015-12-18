package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.entitiy.AddressEntity;
import ro.esock.model.repository.AddressRepository;
import ro.esock.model.repository.GenericRepository;
import ro.esock.model.service.AddressService;

public class AddressServiceImpl extends GenericServiceImpl<AddressEntity, Long> implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Override
	protected GenericRepository<AddressEntity, Long> getRepository() {
		return this.addressRepository;
	}
	
	
}
