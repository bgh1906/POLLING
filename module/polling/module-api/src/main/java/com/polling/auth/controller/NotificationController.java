package com.polling.auth.controller;

import com.polling.aop.annotation.Trace;
import com.polling.auth.service.NotificationService;
import com.polling.grpc.client.dto.request.SendSMSRequestDto;
import com.polling.member.dto.response.SMSCodeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

  private final NotificationService notificationService;

  @Trace
  @PostMapping("/sms")
  public ResponseEntity<SMSCodeResponseDto> sendSMS(
      @RequestBody SendSMSRequestDto requestDto) {
    log.error("requestDto", requestDto.getTo());
    SMSCodeResponseDto response = notificationService.sendSms(requestDto);
    return ResponseEntity.status(200).body(response);
  }
}
