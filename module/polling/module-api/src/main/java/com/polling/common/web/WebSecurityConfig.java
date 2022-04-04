package com.polling.common.web;

import com.polling.auth.service.MemberDetailsService;
import com.polling.security.jwt.JwtAccessDeniedHandler;
import com.polling.security.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig{

  public static String[] SWAGGER_URL_PATHS = new String[]{"/swagger-ui.html**",
      "/swagger-resources/**",
      "/v2/api-docs**", "/webjars/**", "swagger-ui/index.html"};
  private final JwtTokenProvider jwtTokenProvider;
  private final CorsFilter corsFilter;
  private final JwtAuthenticationEntryPoint authenticationErrorHandler;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

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
        .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(authenticationErrorHandler)
        .accessDeniedHandler(jwtAccessDeniedHandler) // https 접근 제어
        // create no session
        .and()
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeRequests((auth) -> auth
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/members/**").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/notify/**").permitAll()
            .antMatchers("/grpc/**").permitAll()
            .antMatchers(SWAGGER_URL_PATHS).permitAll()
            .anyRequest().authenticated())
        .cors();
    return http.build();
  }
}
