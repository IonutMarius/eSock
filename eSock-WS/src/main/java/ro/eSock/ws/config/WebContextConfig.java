package ro.esock.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("ro.esock.ws")
public class WebContextConfig extends WsConfigurerAdapter{
	
	@Bean(name = "user")
	public DefaultWsdl11Definition userWsdl11Definition(XsdSchema userSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UserPort");
		wsdl11Definition.setLocationUri("/ws/");
		wsdl11Definition.setTargetNamespace("http://eSock.ro/ws/soap/user");
		wsdl11Definition.setSchema(userSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "product")
	public DefaultWsdl11Definition productWsdl11Definition(XsdSchema productSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ProductPort");
		wsdl11Definition.setLocationUri("/ws/");
		wsdl11Definition.setTargetNamespace("http://eSock.ro/ws/soap/product");
		wsdl11Definition.setSchema(productSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/user.xsd"));
	}
	
	@Bean
	public XsdSchema productSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/product.xsd"));
	}
}
