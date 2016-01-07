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
	
	
	private static final Long DEFAULT_ID = new Long(0);
	
	@Test
	public void createAddressTest(){
		Address entity = TestUtils.createAddress("_1");
		AddressDTO address = addressConverter.toDto(entity);
		address = addressService.create(address);
		
		Assert.assertNotNull(address);
	}
	
	@Test
	public void findAddressTest(){	
		AddressDTO address = addressService.findById(DEFAULT_ID);
		
		Assert.assertNotNull(address);
	}
	
	@Test
	public void deleteAddressTest(){
		AddressDTO address = addressService.findById(DEFAULT_ID);
		addressService.remove(address);
		address = addressService.findById(address.getAddressId());
		
		Assert.assertNull(address);
	}
	
	@Test
	public void updateAddressTest(){
		AddressDTO expectedAddress = addressService.findById(DEFAULT_ID);
		expectedAddress.setAddressName("addr_0");
		addressService.update(expectedAddress);
		AddressDTO actualAddress = addressService.findById(expectedAddress.getAddressId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
