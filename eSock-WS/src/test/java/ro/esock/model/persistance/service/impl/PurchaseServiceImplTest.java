package ro.esock.model.persistance.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.Order;
import ro.esock.model.entitiy.Product;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.service.OrderService;
import ro.esock.model.service.ProductService;
import ro.esock.model.service.PurchaseService;
import ro.esock.model.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class PurchaseServiceImplTest {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseService purchaseService;

	private User user;
	private Order order;
	private Product product;

	@Before
	@Rollback(false)
	public void addEntities() {
		String sufix = "_1";
		user = userService.create(TestUtils.createUser(sufix));
		
		order = TestUtils.createOrder();
		order.setUser(user);
		order.setAddress(user.getUserProfile().getAddresses().get(0));
		order = orderService.create(order);
		
		product = productService.create(TestUtils.createProduct(sufix));
	}

	@After
	@Rollback(false)
	public void removeEntities() {
		orderService.remove(order);
		userService.remove(user);
		productService.remove(product);
	}

	@Test
	public void saveAndFindPurchaseTest() {
		Purchase expectedPurchase = TestUtils.createPurchase();
		expectedPurchase.setUser(user);
		expectedPurchase.setProduct(product);
		expectedPurchase.setOrder(order);
		purchaseService.create(expectedPurchase);
		Purchase actualPurchase = purchaseService.findById(expectedPurchase.getPurchaseId());

		Assert.assertEquals(expectedPurchase, actualPurchase);
	}

	@Test
	public void saveAndDeletePurchaseTest() {
		Purchase purchase = TestUtils.createPurchase();
		purchase.setUser(user);
		purchase.setProduct(product);
		purchase.setOrder(order);
		purchase = purchaseService.create(purchase);
		purchaseService.remove(purchase);
		purchase = purchaseService.findById(purchase.getPurchaseId());

		Assert.assertEquals(null, purchase);

	}

	@Test
	public void updateAndFindPurchaseTest() {
		Purchase expectedPurchase = TestUtils.createPurchase();
		expectedPurchase.setUser(user);
		expectedPurchase.setProduct(product);
		expectedPurchase.setOrder(order);
		expectedPurchase = purchaseService.create(expectedPurchase);
		
		Product product2 = productService.create(TestUtils.createProduct("_2"));
		expectedPurchase.setProduct(product2);
		
		purchaseService.update(expectedPurchase);
		Purchase actualPurchase = purchaseService.findById(expectedPurchase.getPurchaseId());

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
