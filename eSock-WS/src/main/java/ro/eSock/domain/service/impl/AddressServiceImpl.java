package ro.esock.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.domain.converter.AddressConverter;
import ro.esock.domain.domain.AddressDTO;
import ro.esock.domain.service.AddressService;
import ro.esock.model.entitiy.Address;
import ro.esock.model.repository.AddressRepository;

@Service
public class AddressServiceImpl extends GenericServiceImpl<AddressDTO, Address, Long> implements AddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressConverter addressConverter;

	@Override
	protected AddressRepository getRepository() {
		return this.addressRepository;
	}

	@Override
	protected AddressConverter getEntityConverter() {
		return this.addressConverter;
	}
	
}
