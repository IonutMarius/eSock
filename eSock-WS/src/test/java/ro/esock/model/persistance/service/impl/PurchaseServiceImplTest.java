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

import ro.esock.model.converter.OrderConverter;
import ro.esock.model.converter.ProductConverter;
import ro.esock.model.converter.PurchaseConverter;
import ro.esock.model.converter.UserConverter;
import ro.esock.model.dto.OrderDTO;
import ro.esock.model.dto.ProductDTO;
import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.Product;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
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

	@Autowired
	private OrderConverter orderConverter;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private ProductConverter productConverter;
	@Autowired
	private PurchaseConverter purchaseConverter;

	private UserDTO user;
	private OrderDTO order;
	private ProductDTO product;
	
	private	String sufix = "_1";

	@Before
	@Rollback(false)
	public void addEntities() {
		user = userService.create(userConverter.toDto(TestUtils.createUser(sufix)));
		
		order = orderConverter.toDto(TestUtils.createOrder(sufix));
		order.setAddress(user.getUserProfile().getAddresses().get(0));
		order = orderService.create(order);
		
		product = productService.create(productConverter.toDto(TestUtils.createProduct(sufix)));
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
		Purchase entity = TestUtils.createPurchase();
		PurchaseDTO expectedPurchase = purchaseConverter.toDto(entity);
		expectedPurchase.setUser(user);
		expectedPurchase.setProduct(product);
		expectedPurchase = purchaseService.create(expectedPurchase);
		PurchaseDTO actualPurchase = purchaseService.findById(expectedPurchase.getPurchaseId());

		Assert.assertEquals(expectedPurchase, actualPurchase);
	}

	@Test
	public void saveAndDeletePurchaseTest() {
		Purchase entity = TestUtils.createPurchase();
		PurchaseDTO purchase = purchaseConverter.toDto(entity);
		purchase.setUser(user);
		purchase.setProduct(product);
		purchase = purchaseService.create(purchase);
		purchaseService.remove(purchase);
		purchase = purchaseService.findById(purchase.getPurchaseId());

		Assert.assertEquals(null, purchase);

	}

	@Test
	public void updateAndFindPurchaseTest() {
		Purchase entity = TestUtils.createPurchase();
		PurchaseDTO expectedPurchase = purchaseConverter.toDto(entity);
		expectedPurchase.setUser(user);
		expectedPurchase.setProduct(product);
		expectedPurchase = purchaseService.create(expectedPurchase);
		
		Product entity2 = TestUtils.createProduct("_2");
		ProductDTO product2 = productService.create(productConverter.toDto(entity2));
		expectedPurchase.setProduct(product2);
		
		purchaseService.update(expectedPurchase);
		PurchaseDTO actualPurchase = purchaseService.findById(expectedPurchase.getPurchaseId());

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
