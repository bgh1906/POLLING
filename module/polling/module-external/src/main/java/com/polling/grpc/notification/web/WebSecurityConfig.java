package com.polling.grpc.notification.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

  public static String[] SWAGGER_URL_PATHS = new String[]{"/swagger-ui.html**",
      "/swagger-resources/**",
      "/v2/api-docs**", "/webjars/**", "swagger-ui/index.html"};
  
  @Bean
  @Order(0)
  SecurityFilterChain resources(HttpSecurity http) throws Exception {
    http
        .requestMatchers((matchers) ->
            matchers
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/")
                .antMatchers("/*.html")
                .antMatchers("/favicon.ico")
                .antMatchers("/**/*.html")
                .antMatchers("/**/*.css")
                .antMatchers("/**/*.js")
                .antMatchers("/h2-console/**"))
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
        .requestCache().disable()
        .securityContext().disable()
        .sessionManagement().disable();
    return http.build();
  }

  @Bean
  @Order(1)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .httpBasic().disable()
        .exceptionHandling()
        // create no session
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
        .cors();
    return http.build();
  }


}
