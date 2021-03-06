package ro.esock.model.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.config.JpaHibernateTestConfig;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.repository.PurchaseRepository;
import ro.esock.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class PurchaseRepositoryJpaImplTest {

	@Autowired
	private PurchaseRepository purchaseRepository;

	private static final Long DEFAULT_ID = new Long(0);

	@Test
	public void createPurchase(){
		Purchase purchase = TestUtils.createPurchase("_1");
		purchase = purchaseRepository.create(purchase);
		
		Assert.assertNotNull(purchase);
	}
	
	@Test
	public void findPurchaseTest() {
		Purchase purchase = purchaseRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(purchase);
	}

	@Test
	public void deletePurchaseTest() {
		Purchase purchase = purchaseRepository.findById(DEFAULT_ID);
		purchaseRepository.remove(purchase);
		purchase = purchaseRepository.findById(DEFAULT_ID);

		Assert.assertNull(purchase);

	}

	@Test
	public void updatePurchaseTest() {
		Purchase expectedPurchase = purchaseRepository.findById(DEFAULT_ID);
		expectedPurchase.setQuantity(22);
		purchaseRepository.update(expectedPurchase);
		Purchase actualPurchase = purchaseRepository.findById(DEFAULT_ID);

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
