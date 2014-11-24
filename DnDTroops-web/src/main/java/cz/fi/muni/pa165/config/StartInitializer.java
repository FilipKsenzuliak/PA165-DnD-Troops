package cz.fi.muni.pa165.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.*;
import javax.validation.Validator;

/**
 * Replaces web.xml file, initializes Spring MVC dispatcher servlet, that in turn uses SpringMvcConfig class
 * for Spring MVC configuration.
 *
 * @author David Hubac, Filip Ksenzulia, Andrej Nemex, Tomas
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cz.fi.muni.pa165")
public class StartInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //create Spring beans context configured in  SpringMvcConfig.class
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(this.getClass());

        //register Spring MVC main Dispatcher servlet
        ServletRegistration.Dynamic disp = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        disp.setLoadOnStartup(1);
        disp.addMapping("/mvc/*");

        //register filter setting utf-8 encoding on all requests
        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding", CharacterEncodingFilter.class);
        encoding.setInitParameter("encoding", "utf-8");
        encoding.addMappingForUrlPatterns(null, false, "/*");
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
