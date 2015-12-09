package ro.esock.ws.conf;

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
	
	@Bean(name = "hello")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema helloSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("HelloPort");
		wsdl11Definition.setLocationUri("/ws/");
		wsdl11Definition.setTargetNamespace("http://eSock.ro/ws/resources");
		wsdl11Definition.setSchema(helloSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema helloSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/hello.xsd"));
	}
}
