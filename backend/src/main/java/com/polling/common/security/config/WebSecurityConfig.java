package com.polling.common.security.config;

import com.polling.common.security.jwt.JwtAccessDeniedHandler;
import com.polling.common.security.jwt.JwtAuthenticationEntryPoint;
import com.polling.common.security.jwt.JwtAuthenticationFilter;
import com.polling.common.security.jwt.JwtTokenProvider;
import com.polling.common.security.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final JwtTokenProvider jwtTokenProvider;
   private final CorsFilter corsFilter;
   private final JwtAuthenticationEntryPoint authenticationErrorHandler;
   private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
   private final MemberDetailsService detailsService;
   private final PasswordEncoder passwordEncoder;

   public static String[] SWAGGER_URL_PATHS = new String[] { "/swagger-ui.html**", "/swagger-resources/**",
           "/v2/api-docs**", "/webjars/**", "swagger-ui/index.html" };

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .userDetailsService(detailsService)
              .passwordEncoder(passwordEncoder);
   }

   @Override
   public void configure(WebSecurity web) {
      web.ignoring()
         .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
         .antMatchers(HttpMethod.OPTIONS, "/**")
         .antMatchers(
            "/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/h2-console/**"
         );
      web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**");
      web.ignoring().mvcMatchers(SWAGGER_URL_PATHS);

   }

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity
         // we don't need CSRF because our token is invulnerable
         .csrf().disable()
         .httpBasic().disable()

         .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
         .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)

         .exceptionHandling()
         .authenticationEntryPoint(authenticationErrorHandler)
         .accessDeniedHandler(jwtAccessDeniedHandler) // https 접근 제어

         // create no session
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

         .and()
         .authorizeRequests()
         .antMatchers("/api/auth/**").permitAll()
         .antMatchers("/api/members/**").permitAll()
         .antMatchers(SWAGGER_URL_PATHS).permitAll()

         .anyRequest().authenticated()

         .and().cors();
   }

}
