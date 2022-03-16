package com.polling.common.config;

import com.polling.api.service.member.MemberService;
import com.polling.common.security.jwt.JwtAccessDeniedHandler;
import com.polling.common.security.jwt.JwtAuthenticationEntryPoint;
import com.polling.common.security.jwt.JwtFilter;
import com.polling.common.security.jwt.TokenProvider;
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

   private final TokenProvider tokenProvider;
   private final CorsFilter corsFilter;
   private final JwtAuthenticationEntryPoint authenticationErrorHandler;
   private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
   private final MemberService accountService;
   private final PasswordEncoder passwordEncoder;

   public static String[] SWAGGER_URL_PATHS = new String[] { "/swagger-ui.html**", "/swagger-resources/**",
           "/v2/api-docs**", "/webjars/**" };

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .userDetailsService(accountService)
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
   }

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity
         // we don't need CSRF because our token is invulnerable
         .csrf().disable()
         .httpBasic().disable()

         .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
         .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)

         .exceptionHandling()
         .authenticationEntryPoint(authenticationErrorHandler)
         .accessDeniedHandler(jwtAccessDeniedHandler) // https 접근 제어

         // create no session
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

         .and()
         .authorizeRequests()
//         .antMatchers("/api/auth").permitAll()
//         .antMatchers("/api/user", "/api/user/password/**").permitAll()
//         .antMatchers("/api/notification/mail", "/api/notification/sms").permitAll()
         .antMatchers(SWAGGER_URL_PATHS).permitAll()

//         .antMatchers("/api/**").hasAuthority(UserRoleStatus.ROLE_USER.name())
//         .antMatchers("/api/notice/**").hasAuthority(UserRoleStatus.ROLE_ADMIN.name())

         .anyRequest().authenticated()

         .and().cors();
   }
}
