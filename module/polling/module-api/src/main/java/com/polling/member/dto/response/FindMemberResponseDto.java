package com.polling.member.dto.response;

import com.polling.entity.member.Member;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 유저 정보 반환 DTO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMemberResponseDto {

  @NotNull
  private Long id;
  @NotNull
  private String nickname;
  @NotNull
  private String wallet;
  @NotNull
  private String email;

  public static FindMemberResponseDto of(Member member) {
    return new FindMemberResponseDto(
        member.getId(),
        member.getWallet(),
        member.getNickname(),
        member.getEmail()
    );
  }
}
