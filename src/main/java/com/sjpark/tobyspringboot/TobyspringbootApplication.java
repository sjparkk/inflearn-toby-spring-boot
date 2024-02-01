package com.sjpark.tobyspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class TobyspringbootApplication {

    /**
     * ServletWebServerFactory 없을 시 Error
     Description:

     Web application could not be started as there was no org.springframework.boot.web.servlet.server.ServletWebServerFactory bean defined in the context.

     Action:

     Check your application's dependencies for a supported servlet web server.
     Check the configured web application type.
     */
//    @Bean
//    public ServletWebServerFactory servletWebServerFactory() {
//        return new TomcatServletWebServerFactory();
//    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        SpringApplication.run(TobyspringbootApplication.class, args);
    }
}
