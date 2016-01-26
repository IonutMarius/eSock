package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.domain.converter.PurchaseConverter;
import ro.esock.domain.domain.PurchaseDTO;
import ro.esock.domain.service.PurchaseService;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class PurchaseServiceImplTest {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchaseConverter purchaseConverter;

	private static final Long DEFAULT_ID = new Long(0);

	@Test
	public void createPurchase(){
		Purchase entity = TestUtils.createPurchase("_1");
		PurchaseDTO purchase = purchaseService.create(purchaseConverter.toDto(entity));
		
		Assert.assertNotNull(purchase);
	}
	
	@Test
	public void findPurchaseTest() {
		PurchaseDTO purchase = purchaseService.findById(DEFAULT_ID);

		Assert.assertNotNull(purchase);
	}

	@Test
	public void deletePurchaseTest() {
		PurchaseDTO purchase = purchaseService.findById(DEFAULT_ID);
		purchaseService.remove(purchase);
		purchase = purchaseService.findById(DEFAULT_ID);

		Assert.assertNull(purchase);

	}

	@Test
	public void updatePurchaseTest() {
		PurchaseDTO expectedPurchase = purchaseService.findById(DEFAULT_ID);
		expectedPurchase.setQuantity(22);
		purchaseService.update(expectedPurchase);
		PurchaseDTO actualPurchase = purchaseService.findById(DEFAULT_ID);

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
