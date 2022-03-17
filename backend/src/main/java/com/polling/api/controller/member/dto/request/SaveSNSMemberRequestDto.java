package com.polling.api.controller.member.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveSNSMemberRequestDto {
    @NotNull
    private String nickname;
    @NotNull
    private String email;
    @NotNull
    private String accessToken;
    @NotNull
    private String phoneNumber;
}
