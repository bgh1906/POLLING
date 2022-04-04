package com.polling.grpc.client.dto.request;

import com.polling.grpc.GiftType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRequestDto {

  String userEmail;
  GiftType giftType;
}
