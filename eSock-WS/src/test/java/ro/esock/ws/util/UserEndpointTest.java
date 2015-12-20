package ro.esock.ws.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.esock.model.persistance.config.JpaHibernateTestConfig;
import ro.esock.ws.config.WebContextTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class, WebContextTestConfig.class})
//@Transactional
public class UserEndpointTest {

}
