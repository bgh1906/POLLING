package com.polling.security.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuthClient {

  private final WebClient webClient;


  public KaKaoOAuthResponse getInfo(String accessToken) {

    KaKaoOAuthResponse response = webClient.post()
        .uri("https://kapi.kakao.com/v2/user/me?secure_resource=false")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError,
            clientResponse -> Mono.error(new IllegalArgumentException("액세스 토큰 실패")))
        .onStatus(HttpStatus::is5xxServerError,
            clientResponse -> Mono.error(new IllegalArgumentException("로그인 과정 실패")))
        .bodyToMono(KaKaoOAuthResponse.class)
        .block();
    return response;

  }


}
