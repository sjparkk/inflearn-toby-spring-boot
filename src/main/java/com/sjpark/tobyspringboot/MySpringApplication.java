package com.sjpark.tobyspringboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory servletWebServerFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                //없더라도 스프링 컨테이너가 주입을 해줘서 사용이 가능함.
                dispatcherServlet.setApplicationContext(this);

                WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh();

        //http -v ":8080/hello?name=Spring"
        //http -v POST ":8080/hello?name=Spring"
    }
}
