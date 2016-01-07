package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.converter.OrderConverter;
import ro.esock.model.converter.ProductConverter;
import ro.esock.model.dto.OrderDTO;
import ro.esock.model.dto.ProductDTO;
import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.entitiy.Order;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;
import ro.esock.model.service.OrderService;
import ro.esock.model.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class OrderServiceImplTest {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderConverter orderConverter;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private ProductService productService;

	private static final Long DEFAULT_ID = new Long(0);

	@Test
	public void createOrderTest(){
		Order entity = TestUtils.createOrder("_1");
		OrderDTO order = orderConverter.toDto(entity);
		order = orderService.create(order);
		
		Assert.assertNotNull(order);
	}

	@Test
	public void findOrderTest() {
		OrderDTO order = orderService.findById(DEFAULT_ID);

		Assert.assertNotNull(order);
	}

	@Test
	public void deleteOrderTest() {
		OrderDTO order = orderService.findById(DEFAULT_ID);
		orderService.remove(order);
		order = orderService.findById(order.getOrderId());

		Assert.assertNull(order);

	}

	@Test
	public void updateOrderTest() {
		OrderDTO expectedOrder = orderService.findById(DEFAULT_ID);
		PurchaseDTO purchaseDto = new PurchaseDTO();
		ProductDTO newProduct = productService.create(productConverter.toDto(TestUtils.createProduct("_1")));
		purchaseDto.setProduct(newProduct);
		purchaseDto.setQuantity(2);
		purchaseDto.setUser(expectedOrder.getPurchases().get(0).getUser());
		expectedOrder.getPurchases().add(purchaseDto);
		orderService.update(expectedOrder);
		OrderDTO actualOrder = orderService.findById(expectedOrder.getOrderId());

		Assert.assertEquals(expectedOrder.getPurchases().size(), actualOrder.getPurchases().size());

	}
}
