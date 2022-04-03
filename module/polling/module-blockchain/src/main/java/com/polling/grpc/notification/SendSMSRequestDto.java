package com.polling.grpc.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendSMSRequestDto {

  @NotNull
  public String to;
  @NotNull
  public String content;
}
