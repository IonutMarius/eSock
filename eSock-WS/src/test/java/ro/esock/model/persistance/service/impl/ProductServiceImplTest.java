package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.entitiy.Product;
import ro.esock.model.persistance.TestUtils;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class ProductServiceImplTest {

	@Autowired
	private ProductService productService;

	private String sufix = "_1";

	@Test
	public void saveAndFindProductTest() {
		Product expectedProduct = TestUtils.createProduct(sufix);
		productService.create(expectedProduct);
		Product product = productService.findById(expectedProduct.getProductId());

		Assert.assertEquals(expectedProduct, product);
	}

	@Test
	public void saveAndDeleteProductTest() {
		Product product = TestUtils.createProduct(sufix);
		product = productService.create(product);
		productService.remove(product);
		product = productService.findById(product.getProductId());

		Assert.assertEquals(null, product);

	}

	@Test
	public void updateAndFindProductTest() {
		Product expectedProduct = TestUtils.createProduct(sufix);
		expectedProduct = productService.create(expectedProduct);
		expectedProduct.setDescription("changed description_0");
		productService.update(expectedProduct);
		Product actualProduct = productService.findById(expectedProduct.getProductId());

		Assert.assertEquals(expectedProduct, actualProduct);

	}
}
