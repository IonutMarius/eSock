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
import ro.esock.model.converter.UserConverter;
import ro.esock.model.dto.OrderDTO;
import ro.esock.model.dto.ProductDTO;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.Order;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
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

	@Autowired
	private UserConverter userConverter;
	@Autowired
	private ProductConverter productConverter;
	@Autowired
	private OrderConverter orderConverter;

	private UserDTO user;
	private ProductDTO product1;
	private ProductDTO product2;

	private String sufix = "_1";
	private String sufix2 = "_2";

	@Before
	@Rollback(false)
	public void addEntities() {
		user = userService.create(userConverter.toDto(TestUtils.createUser(sufix)));

		product1 = productService.create(productConverter.toDto(TestUtils.createProduct(sufix)));
		product2 = productService.create(productConverter.toDto(TestUtils.createProduct(sufix2)));
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
		Order entity = TestUtils.createOrder(sufix);
		OrderDTO expectedOrder = orderConverter.toDto(entity);
		expectedOrder.setAddress(user.getUserProfile().getAddresses().get(0));

		expectedOrder.getPurchases().get(0).setProduct(product1);
		expectedOrder.getPurchases().get(1).setProduct(product2);

		expectedOrder.getPurchases().get(0).setUser(user);
		expectedOrder.getPurchases().get(1).setUser(user);

		orderService.create(expectedOrder);
		OrderDTO actualOrder = orderService.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);
	}

	@Test
	public void saveAndDeleteOrderTest() {
		Order entity = TestUtils.createOrder(sufix);
		OrderDTO order = orderConverter.toDto(entity);
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
		Order entity = TestUtils.createOrder(sufix);
		OrderDTO expectedOrder = orderConverter.toDto(entity);
		expectedOrder.setAddress(user.getUserProfile().getAddresses().get(0));

		expectedOrder.getPurchases().get(0).setProduct(product1);
		expectedOrder.getPurchases().get(1).setProduct(product2);

		expectedOrder.getPurchases().get(0).setUser(user);
		expectedOrder.getPurchases().get(1).setUser(user);

		expectedOrder = orderService.create(expectedOrder);
		expectedOrder.getAddress().setAddressName("addr_0");
		orderService.update(expectedOrder);
		OrderDTO actualOrder = orderService.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);

	}
}
