package ro.esock.model.persistance.repository.impl;

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
import ro.esock.model.repository.AddressRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class AddressRepositoryJpaImplTest {
	@Autowired
	private AddressRepository addressRepository;
	
	@Test
	public void saveAndFindAddressTest(){
		Address expectedAddress = TestUtils.createAddress("_1");
		addressRepository.create(expectedAddress);	
		Address actualAddress = addressRepository.findById(expectedAddress.getAddressId());
		
		Assert.assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
	public void saveAndDeleteAddressTest(){
		Address address = addressRepository.create(TestUtils.createAddress("_1"));
		addressRepository.remove(address);
		address = addressRepository.findById(address.getAddressId());
		
		Assert.assertEquals(null, address);
	}
	
	@Test
	public void updateAndFindAddressTest(){
		Address expectedAddress = addressRepository.create(TestUtils.createAddress("_1"));
		expectedAddress.setAddressName("addr_0");
		addressRepository.update(expectedAddress);
		Address actualAddress = addressRepository.findById(expectedAddress.getAddressId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
