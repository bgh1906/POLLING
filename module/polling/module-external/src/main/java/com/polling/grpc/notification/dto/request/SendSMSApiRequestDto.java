package com.polling.grpc.notification.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendSMSApiRequestDto {

  private String type;
  private String contentType;
  private String countryCode;
  private String from;
  private String content;
  private List<SendSMSRequestDto> messages;
}
