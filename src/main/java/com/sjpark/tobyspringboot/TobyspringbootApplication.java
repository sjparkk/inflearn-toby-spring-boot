package com.sjpark.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobyspringbootApplication {

    public static void main(String[] args) {

//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        //추상화
        ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().println("Hello Servlet");
                }
            }).addMapping("/hello");
        });
        //톰캣 서블릿 컨테이너 동작
        webServer.start();

        //실행 시 -> Tomcat started on port(s): 8080 (http) with context path ''

        /*
        터미널에서 'http -v :8080' 을 통해 다음 정보 확인 가능 (* 명령어가 동작하지 않는 다면 brew install httpie 명령어를 통해 설치!)

        GET / HTTP/1.10
        Accept-Encoding: gzip, deflate
        Connection: keep-alive
        Host: localhost:8080
        User-Agent: HTTPie/3.2.2

        HTTP/1.1 404
        Connection: keep-alive
        Content-Language: en
        Content-Length: 682
        Content-Type: text/html;charset=utf-8
        Date: Wed, 02 Aug 2023 14:14:15 GMT
        Keep-Alive: timeout=60

        <!doctype html><html lang="en"><head><title>HTTP Status 404 – Not Found</title><style type="text/css">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP Status 404 – Not Found</h1><hr class="line" /><p><b>Type</b> Status Report</p><p><b>Description</b> The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.</p><hr class="line" /><h3>Apache Tomcat/9.0.75</h3></body></html>
        * */
    }

}
