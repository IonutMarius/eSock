package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.converter.AddressConverter;
import ro.esock.model.dto.AddressDTO;
import ro.esock.model.entitiy.Address;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class AddressServiceImplTest {
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private AddressConverter addressConverter;
	
	@Test
	public void saveAndFindAddressTest(){
		Address address = TestUtils.createAddressWithNoRelation("_1");
		AddressDTO expectedAddress = addressConverter.toDto(address);
		addressService.create(expectedAddress);	
		AddressDTO actualAddress = addressService.findById(expectedAddress.getAddressId());
		
		Assert.assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
	public void saveAndDeleteAddressTest(){
		Address entity = TestUtils.createAddressWithNoRelation("_1");
		AddressDTO address = addressService.create(addressConverter.toDto(entity));
		addressService.remove(address);
		address = addressService.findById(address.getAddressId());
		
		Assert.assertEquals(null, address);
	}
	
	@Test
	public void updateAndFindAddressTest(){
		Address address = TestUtils.createAddressWithNoRelation("_1");
		AddressDTO expectedAddress = addressConverter.toDto(address);
		expectedAddress.setAddressName("addr_0");
		addressService.update(expectedAddress);
		AddressDTO actualAddress = addressService.findById(expectedAddress.getAddressId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
