package com.polling.api.controller.member.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveSNSMemberRequestDto {
    private String name;
    private String email;
    private String accessToken;
    private String phoneNumber;
}
