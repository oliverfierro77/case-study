package com.apixandru.casestudy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@SpringBootApplication
public class SpringBootWeblogicApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWeblogicApplication.class);
    }

    @Bean
    public FilterRegistrationBean fixIeHttpsFontCache() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new NoCacheFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/b.txt");
        return registration;
    }

}
