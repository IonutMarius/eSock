package ro.esock.model.persistance.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.domain.converter.ProductConverter;
import ro.esock.domain.domain.ProductDTO;
import ro.esock.domain.service.ProductService;
import ro.esock.model.entitiy.Product;
import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.model.persistance.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class ProductServiceImplTest {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConverter productConverter;

	private String sufix = "_1";
	private static final Long DEFAULT_ID = new Long(0);

	@Test
	public void createProductTest(){
		Product entity = TestUtils.createProduct(sufix);
		ProductDTO actualProduct = productConverter.toDto(entity);
		ProductDTO expectedProduct = productService.create(actualProduct);
		actualProduct.setProductId(expectedProduct.getProductId());
		
		Assert.assertEquals(expectedProduct, actualProduct);
	}
	
	@Test
	public void findProductTest() {
		ProductDTO product = productService.findById(DEFAULT_ID);

		Assert.assertNotNull(product);
	}

	@Test
	public void deleteProductTest() {
		ProductDTO product = productService.findById(DEFAULT_ID);
		productService.remove(product);
		product = productService.findById(product.getProductId());

		Assert.assertNull(product);

	}

	@Test
	public void updateProductTest() {
		ProductDTO expectedProduct = productService.findById(DEFAULT_ID);
		expectedProduct.setDescription("changed description_0");
		productService.update(expectedProduct);
		ProductDTO actualProduct = productService.findById(DEFAULT_ID);

		Assert.assertEquals(expectedProduct, actualProduct);

	}
}
