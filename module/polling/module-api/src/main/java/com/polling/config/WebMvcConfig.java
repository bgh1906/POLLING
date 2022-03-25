package com.polling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final List<String> exposedHeader
            = Arrays.asList(new String[]{ "refreshToken", "2", "3" });

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://j6a304.p.ssafy.io/")
                .exposedHeaders("refreshToken", "accessToken")

        ;

    }
}
