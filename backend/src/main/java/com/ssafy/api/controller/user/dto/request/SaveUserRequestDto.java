package com.ssafy.api.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Native login 기준으로 작성
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveUserRequestDto {
    private String name;
    private String email;
    //sns 로그인 시 password 필드는 주석처리해주시고 acess_token을 받아주세요
    private String password;
    private String phoneNumber;
}
