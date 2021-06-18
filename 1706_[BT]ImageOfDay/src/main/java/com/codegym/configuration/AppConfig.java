package com.codegym.configuration;

import groovy.transform.AnnotationCollector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.codegym.controller")
public class AppConfig implements WebMvcConfigurer, AnnotationCollector {

}
