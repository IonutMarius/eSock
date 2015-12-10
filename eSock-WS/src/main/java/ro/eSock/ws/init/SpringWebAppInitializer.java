package ro.esock.ws.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import ro.esock.model.persistance.config.JpaHibernateConfig;
import ro.esock.ws.conf.WebContextConfig;
 
public class SpringWebAppInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebContextConfig.class);
        appContext.register(JpaHibernateConfig.class);
         
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet(appContext);
        messageDispatcherServlet.setApplicationContext(appContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringMessageDispatcherServlet", messageDispatcherServlet);
        dispatcher.addMapping("/ws/*");
        dispatcher.setLoadOnStartup(1);
    }
}