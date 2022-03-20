package com.polling.auth.dto;

import com.polling.entity.member.status.OAuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthDto {
    @NotNull
    private String nickname;
    @NotNull
    private String accessToken;
    @NotNull
    private OAuthType oAuthType;
//    @NotNull
//    private String refreshToken;
    @NotNull
    private String phoneNumber;
}
