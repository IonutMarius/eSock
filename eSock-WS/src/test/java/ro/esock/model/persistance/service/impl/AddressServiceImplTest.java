package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.Address;
import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class AddressServiceImplTest {
	@Autowired
	private AddressService addressService;
	
	@Test
	public void saveAndFindAddressTest(){
		Address expectedAddress = TestUtils.createAddress("_1");
		addressService.create(expectedAddress);	
		Address actualAddress = addressService.findById(expectedAddress.getAddressId());
		
		Assert.assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
	public void saveAndDeleteAddressTest(){
		Address address = addressService.create(TestUtils.createAddress("_1"));
		addressService.remove(address);
		address = addressService.findById(address.getAddressId());
		
		Assert.assertEquals(null, address);
	}
	
	@Test
	public void updateAndFindAddressTest(){
		Address expectedAddress = addressService.create(TestUtils.createAddress("_1"));
		expectedAddress.setAddressName("addr_0");
		addressService.update(expectedAddress);
		Address actualAddress = addressService.findById(expectedAddress.getAddressId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
