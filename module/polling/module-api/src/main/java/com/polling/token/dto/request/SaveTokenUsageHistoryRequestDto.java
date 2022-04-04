package com.polling.token.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTokenUsageHistoryRequestDto {

  @NotNull
  private Long candidateId;

}
