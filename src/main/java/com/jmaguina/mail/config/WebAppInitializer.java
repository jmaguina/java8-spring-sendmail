package com.jmaguina.mail.config;

/**
 * Created by Jmaguina on 28/10/2017.
 */

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext rootContext = createRootContext(servletContext);

        configureSpringMvc(servletContext, rootContext);

        //CorsFilter
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");
    }

    private WebApplicationContext createRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

        //rootContext.register(WebSecurityConfig.class, JPAConfiguration.class, AppConfig.class);
        rootContext.register(AppConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        return rootContext;
    }

    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(AppConfig.class);

        mvcContext.setParent(rootContext);
        ServletRegistration.Dynamic appServlet = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(mvcContext));
        appServlet.addMapping("/");
        //IMportante websockets
        appServlet.setAsyncSupported(true);
        appServlet.setLoadOnStartup(1);


    }


}