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
import ro.esock.model.entitiy.User;
import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.service.OrderService;
import ro.esock.model.service.ProductService;
import ro.esock.model.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class OrderServiceImplTest {
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	private User user;
	private Product product1;
	private Product product2;

	@Before
	@Rollback(false)
	public void addEntities() {
		String sufix = "_1";
		String sufix2 = "_2";

		user = userService.create(TestUtils.createUser(sufix));

		product1 = productService.create(TestUtils.createProduct(sufix));
		product2 = productService.create(TestUtils.createProduct(sufix2));
	}

	@After
	@Rollback(false)
	public void removeEntities() {
		productService.remove(product2);
		productService.remove(product1);
		userService.remove(user);
	}

	@Test
	public void saveAndFindOrderTest() {
		Order expectedOrder = TestUtils.createOrder();
		expectedOrder.setUser(user);
		expectedOrder.setAddress(user.getUserProfile().getAddresses().get(0));

		expectedOrder.getPurchases().get(0).setProduct(product1);
		expectedOrder.getPurchases().get(1).setProduct(product2);

		expectedOrder.getPurchases().get(0).setUser(user);
		expectedOrder.getPurchases().get(1).setUser(user);

		orderService.create(expectedOrder);
		Order actualOrder = orderService.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);
	}

	@Test
	public void saveAndDeleteOrderTest() {
		Order order = TestUtils.createOrder();
		order.setUser(user);
		order.setAddress(user.getUserProfile().getAddresses().get(0));

		order.getPurchases().get(0).setProduct(product1);
		order.getPurchases().get(1).setProduct(product2);

		order.getPurchases().get(0).setUser(user);
		order.getPurchases().get(1).setUser(user);

		order = orderService.create(order);
		orderService.remove(order);
		order = orderService.findById(order.getOrderId());

		Assert.assertEquals(null, order);

	}

	@Test
	public void updateAndFindOrderTest() {
		Order expectedOrder = TestUtils.createOrder();
		expectedOrder.setUser(user);
		expectedOrder.setAddress(user.getUserProfile().getAddresses().get(0));

		expectedOrder.getPurchases().get(0).setProduct(product1);
		expectedOrder.getPurchases().get(1).setProduct(product2);

		expectedOrder.getPurchases().get(0).setUser(user);
		expectedOrder.getPurchases().get(1).setUser(user);

		expectedOrder = orderService.create(expectedOrder);
		expectedOrder.getAddress().setAddressName("addr_0");
		orderService.update(expectedOrder);
		Order actualOrder = orderService.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);

	}
}
