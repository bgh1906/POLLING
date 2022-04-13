package com.polling.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 휴대폰 인증 코드 반환 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSCodeResponseDto {

  private String code;
}
