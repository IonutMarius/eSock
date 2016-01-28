package ro.esock.model.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.config.JpaHibernateTestConfig;
import ro.esock.model.entitiy.Order;
import ro.esock.model.repository.OrderRepository;
import ro.esock.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class OrderRepositoryJpaImplTest {
	
	@Autowired
	private OrderRepository orderRepository;

	private static final Long DEFAULT_ID = new Long(0);

	@Test
	public void createOrderTest(){
		Order order = TestUtils.createOrder("_1");
		order = orderRepository.create(order);
		
		Assert.assertNotNull(order);
	}

	@Test
	public void findOrderTest() {
		Order order = orderRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(order);
	}

	@Test
	public void deleteOrderTest() {
		Order order = orderRepository.findById(DEFAULT_ID);
		orderRepository.remove(order);
		order = orderRepository.findById(order.getOrderId());

		Assert.assertNull(order);

	}

	@Test
	public void updateOrderTest() {
		Order expectedOrder = orderRepository.findById(DEFAULT_ID);
		expectedOrder.getAddress().setAddressName("addr_-1");
		orderRepository.update(expectedOrder);
		Order actualOrder = orderRepository.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder, actualOrder);

	}
}
