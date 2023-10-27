package ru.netology;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.netology.config.WebAppConfig;

import javax.servlet.Servlet;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(context);
        Context servletContext = tomcat.addContext("", System.getProperty("java.io.tmpdir"));
        Tomcat.addServlet(servletContext, "dispatcherServlet", (Servlet) servlet);
        servletContext.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
