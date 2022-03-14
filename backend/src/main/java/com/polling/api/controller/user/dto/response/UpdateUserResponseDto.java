package com.polling.api.controller.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponseDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
