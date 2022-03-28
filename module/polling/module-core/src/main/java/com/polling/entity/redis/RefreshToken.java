package com.polling.entity.redis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

  @Id
  @Column(nullable = false)
  private String refreshToken;

  public RefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}