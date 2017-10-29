package com.jmaguina.mail.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Jorge Maguina on 28/10/2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jmaguina.mail.app")
public class AppConfig {

}
