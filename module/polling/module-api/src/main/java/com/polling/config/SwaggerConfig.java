package com.polling.config;


import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * API 문서 관련 swagger2 설정 정의.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

  /**
   * 스웨거 Path Config
   */
  @Bean
  public Docket swaggerMainApi() {
    return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
        .groupName("Main")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.polling"))
        .paths(PathSelectors.ant("/api/**"))
        .build()
        .apiInfo(new ApiInfoBuilder().version("1.0").title("Main API").build())
        .securityContexts(List.of(securityContext()))
        .securitySchemes(List.of(apiKey()));
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", "authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext
        .builder()
        .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global",
        "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return List.of(new SecurityReference("JWT", authorizationScopes));
  }

  @Bean
  UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
        .build();
  }
}