package com.example.springforumapp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/static/**", "/upload/files/**")
                .addResourceLocations("classpath:/static/", "file:" + System.getProperty("user.dir") + "/upload/files/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
               .allowedOriginPatterns("*")
               .allowedMethods("*")
               .allowedHeaders("*")
               .allowCredentials(true);
    }

}

