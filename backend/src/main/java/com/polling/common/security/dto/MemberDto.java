package com.polling.common.security.dto;

import com.polling.core.entity.member.status.MemberRole;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
public class MemberDto {
    private Long id;

    @NotNull
    private String nickname;

    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    private Set<MemberRole> memberRole;

    private LocalDateTime createDate;

}
