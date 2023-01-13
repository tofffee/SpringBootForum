package com.example.springforumapp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**", "/upload/files/videos/**")
//                .addResourceLocations("classpath:/static/", "file:" + System.getProperty("user.dir") + "/upload/files/videos/");
        registry.addResourceHandler("/static/**", "/upload/files/**")
                .addResourceLocations("classpath:/static/", "file:" + System.getProperty("user.dir") + "/upload/files/");
    }
}

