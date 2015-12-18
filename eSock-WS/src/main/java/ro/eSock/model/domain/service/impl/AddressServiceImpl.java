package ro.esock.model.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ro.esock.model.domain.service.AddressService;
import ro.esock.model.persistance.entitiy.AddressEntity;
import ro.esock.model.persistance.repository.AddressRepository;
import ro.esock.model.persistance.repository.GenericRepository;

public class AddressServiceImpl extends GenericServiceImpl<AddressEntity, Long> implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Override
	protected GenericRepository<AddressEntity, Long> getRepository() {
		return this.addressRepository;
	}
	
	
}
