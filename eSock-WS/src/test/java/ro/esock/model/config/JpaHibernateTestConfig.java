package ro.esock.model.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ro.esock.model.config.RootJpaHibernateConfig;

@Configuration
@Import(RootJpaHibernateConfig.class)
@PropertySource("classpath:test.properties")
public class JpaHibernateTestConfig {
	
}