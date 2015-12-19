package ro.esock.model.persistance.repository.impl;

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
import ro.esock.model.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class ProductRepositoryJpaImplTest {

	@Autowired
	private ProductRepository productRepository;

	private String sufix = "_1";

	@Test
	public void saveAndFindProductTest() {
		Product expectedProduct = TestUtils.createProduct(sufix);
		productRepository.create(expectedProduct);
		Product product = productRepository.findById(expectedProduct.getProductId());

		Assert.assertEquals(expectedProduct, product);
	}

	@Test
	public void saveAndDeleteProductTest() {
		Product product = TestUtils.createProduct(sufix);
		product = productRepository.create(product);
		productRepository.remove(product);
		product = productRepository.findById(product.getProductId());

		Assert.assertEquals(null, product);

	}

	@Test
	public void updateAndFindProductTest() {
		Product expectedProduct = TestUtils.createProduct(sufix);
		expectedProduct = productRepository.create(expectedProduct);
		expectedProduct.setDescription("changed description_0");
		productRepository.update(expectedProduct);
		Product actualProduct = productRepository.findById(expectedProduct.getProductId());

		Assert.assertEquals(expectedProduct, actualProduct);

	}
}
