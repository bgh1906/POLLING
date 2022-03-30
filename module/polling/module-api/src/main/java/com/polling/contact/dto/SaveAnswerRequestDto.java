package com.polling.contact.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAnswerRequestDto {

  @NotNull
  private Long contactId;
  @NotNull
  private String answer;
}
