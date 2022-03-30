package com.polling.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAnswerRequestDto {
    @NotNull
    private Long contactId;
    @NotNull
    private String answer;
}
