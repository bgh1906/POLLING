package com.polling.member.dto.request;

import com.polling.entity.member.status.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveNativeMemberRequestDto {
    @NotNull
    private String nickname;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;

    private MemberRole role;
}
