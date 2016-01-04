package ro.esock.model.converter;

import org.springframework.stereotype.Component;

import ro.esock.model.dto.AddressDTO;
import ro.esock.model.entitiy.Address;

@Component
public class AddressConverter extends GenericEntityConverter<AddressDTO, Address> {

	@Override
	public AddressDTO toDto(Address entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address toEntity(AddressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
