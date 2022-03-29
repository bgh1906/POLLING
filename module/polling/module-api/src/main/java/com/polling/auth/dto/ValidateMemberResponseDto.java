package com.polling.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateMemberResponseDto {
    private boolean member;
}
