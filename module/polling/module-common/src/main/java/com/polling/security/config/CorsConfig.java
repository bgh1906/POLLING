package com.polling.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;


@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.addAllowedOrigin("http://localhost:3000");
      config.addAllowedOrigin("https://j6a304.p.ssafy.io");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      config.setAllowedOriginPatterns(Collections.singletonList("*"));
      config.setAllowCredentials(true);
      config.addExposedHeader("authorization");
      config.addExposedHeader("refreshToken");
      config.setMaxAge(3600L);
      source.registerCorsConfiguration("/api/**", config);
      return new CorsFilter(source);
   }

}
