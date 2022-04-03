package com.polling.auth.dto;

import com.polling.member.entity.status.MemberRole;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * Security 세션에 저장되어있는 로그인된 사용자 정보
 */
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
