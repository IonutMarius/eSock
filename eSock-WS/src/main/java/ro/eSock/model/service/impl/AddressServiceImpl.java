package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.esock.model.converter.AddressConverter;
import ro.esock.model.dto.AddressDTO;
import ro.esock.model.entitiy.Address;
import ro.esock.model.repository.AddressRepository;
import ro.esock.model.service.AddressService;

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
