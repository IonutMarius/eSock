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
import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.repository.OrderRepository;
import ro.esock.model.repository.ProductRepository;
import ro.esock.model.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class OrderRepositoryJpaImplTest {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	private User user;
	private Product product1;
	private Product product2;

	private String sufix = "_1";
	private String sufix2 = "_2";

	@Before
	@Rollback(false)
	public void addEntities() {

		user = userRepository.create(TestUtils.createUser(sufix));

		product1 = productRepository.create(TestUtils.createProduct(sufix));
		product2 = productRepository.create(TestUtils.createProduct(sufix2));
	}

	@After
	@Rollback(false)
	public void removeEntities() {
		productRepository.remove(product2);
		productRepository.remove(product1);
		userRepository.remove(user);
	}

	@Test
	public void saveAndFindOrderTest() {
		Order expectedOrder = TestUtils.createOrder(sufix);
		expectedOrder.setAddress(user.getUserProfile().getAddresses().get(0));

		expectedOrder.getPurchases().get(0).setProduct(product1);
		expectedOrder.getPurchases().get(1).setProduct(product2);

		orderRepository.create(expectedOrder);
		Order actualOrder = orderRepository.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);
	}

	@Test
	public void saveAndDeleteOrderTest() {
		Order order = TestUtils.createOrder(sufix);
		order.setAddress(user.getUserProfile().getAddresses().get(0));

		order.getPurchases().get(0).setProduct(product1);
		order.getPurchases().get(1).setProduct(product2);

		order = orderRepository.create(order);
		orderRepository.remove(order);
		order = orderRepository.findById(order.getOrderId());

		Assert.assertEquals(null, order);

	}

	@Test
	public void updateAndFindOrderTest() {
		Order expectedOrder = TestUtils.createOrder(sufix);
		expectedOrder.setAddress(user.getUserProfile().getAddresses().get(0));

		expectedOrder.getPurchases().get(0).setProduct(product1);
		expectedOrder.getPurchases().get(0).setOrder(expectedOrder);
		expectedOrder.getPurchases().get(1).setProduct(product2);
		expectedOrder.getPurchases().get(1).setOrder(expectedOrder);
		
		expectedOrder.getPurchases().get(0).setUser(user);
		expectedOrder.getPurchases().get(1).setUser(user);
		
		expectedOrder.setUser(user);

		expectedOrder = orderRepository.create(expectedOrder);
		expectedOrder.getAddress().setAddressName("addr_0");
		orderRepository.update(expectedOrder);
		Order actualOrder = orderRepository.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);

	}
}
