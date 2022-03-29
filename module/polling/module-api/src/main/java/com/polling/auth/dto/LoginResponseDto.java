package com.polling.auth.dto;

import com.polling.entity.member.status.MemberRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {
    private MemberRole role;
    private String nickname;
}
