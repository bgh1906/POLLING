package com.polling.auth.dto;

import com.polling.entity.member.status.MemberRole;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
