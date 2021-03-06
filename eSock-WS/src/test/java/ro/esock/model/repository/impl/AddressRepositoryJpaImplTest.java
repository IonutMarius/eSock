package ro.esock.model.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.config.JpaHibernateTestConfig;
import ro.esock.model.entitiy.Address;
import ro.esock.model.repository.AddressRepository;
import ro.esock.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class AddressRepositoryJpaImplTest {
	
	@Autowired
	private AddressRepository addressRepository;
	
	private static final Long DEFAULT_ID = new Long(0);
	
	@Test
	public void createAddressTest(){
		Address address = TestUtils.createAddress("_1");
		address = addressRepository.create(address);
		
		Assert.assertNotNull(address);
	}
	
	@Test
	public void findAddressTest(){	
		Address address = addressRepository.findById(DEFAULT_ID);
		
		Assert.assertNotNull(address);
	}
	
	@Test
	public void deleteAddressTest(){
		Address address = addressRepository.findById(DEFAULT_ID);
		addressRepository.remove(address);
		address = addressRepository.findById(address.getAddressId());
		
		Assert.assertNull(address);
	}
	
	@Test
	public void updateAddressTest(){
		Address expectedAddress = addressRepository.findById(DEFAULT_ID);
		expectedAddress.setAddressName("addr_0");
		addressRepository.update(expectedAddress);
		Address actualAddress = addressRepository.findById(expectedAddress.getAddressId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
