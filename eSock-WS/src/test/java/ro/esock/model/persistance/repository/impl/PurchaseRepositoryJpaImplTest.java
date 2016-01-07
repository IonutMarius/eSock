package ro.esock.model.persistance.repository.impl;

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
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.repository.OrderRepository;
import ro.esock.model.repository.ProductRepository;
import ro.esock.model.repository.PurchaseRepository;
import ro.esock.model.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class PurchaseRepositoryJpaImplTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	private User user;
	private Order order;
	private Product product;

	private String sufix = "_1";

	@Before
	@Rollback(false)
	public void addEntities() {
		user = userRepository.create(TestUtils.createUser(sufix));

		order = TestUtils.createOrder(sufix);
		order.setAddress(user.getUserProfile().getAddresses().get(0));
		order = orderRepository.create(order);

		product = productRepository.create(TestUtils.createProduct(sufix));
	}

	@After
	@Rollback(false)
	public void removeEntities() {
		orderRepository.remove(order);
		userRepository.remove(user);
		productRepository.remove(product);
	}

	@Test
	public void saveAndFindPurchaseTest() {
		Purchase expectedPurchase = TestUtils.createPurchase();
		expectedPurchase.setUser(user);
		expectedPurchase.setProduct(product);
		purchaseRepository.create(expectedPurchase);
		Purchase actualPurchase = purchaseRepository.findById(expectedPurchase.getPurchaseId());

		Assert.assertEquals(expectedPurchase, actualPurchase);
	}

	@Test
	public void saveAndDeletePurchaseTest() {
		Purchase purchase = TestUtils.createPurchase();
		purchase.setUser(user);
		purchase.setProduct(product);
		purchase = purchaseRepository.create(purchase);
		purchaseRepository.remove(purchase);
		purchase = purchaseRepository.findById(purchase.getPurchaseId());

		Assert.assertEquals(null, purchase);

	}

	@Test
	public void updateAndFindPurchaseTest() {
		Purchase expectedPurchase = TestUtils.createPurchase();
		expectedPurchase.setUser(user);
		expectedPurchase.setProduct(product);
		expectedPurchase = purchaseRepository.create(expectedPurchase);

		Product product2 = productRepository.create(TestUtils.createProduct("_2"));
		expectedPurchase.setProduct(product2);

		purchaseRepository.update(expectedPurchase);
		Purchase actualPurchase = purchaseRepository.findById(expectedPurchase.getPurchaseId());

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
