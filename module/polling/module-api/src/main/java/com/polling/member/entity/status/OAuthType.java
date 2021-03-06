package com.polling.member.entity.status;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OAuthType {
  KAKAO("kakaoAuthProvider"),
  NAVER("naverAuthProvider");

  private final String oAuthProviderName;

  OAuthType(String oAuthProviderName) {
    this.oAuthProviderName = oAuthProviderName;
  }

  public OAuthType findByType(String oAuthTypeName) {
    return Arrays.stream(OAuthType.values())
        .filter(oauth -> oauth.name().equals(oAuthTypeName.toUpperCase()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(oAuthTypeName + ": 존재하지 않는 회원입니다"));
  }

}
