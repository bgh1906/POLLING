package com.polling.member.controller;

import com.polling.aop.annotation.Retry;
import com.polling.member.dto.response.SMSCodeResponseDto;
import com.polling.member.service.NotificationService;
import com.polling.util.notification.SendSMSRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notify")
public class NotificationController {

  private final NotificationService notificationService;

  @Retry
  @PostMapping("/sms")
  @ApiOperation(value = "휴대폰 인증")
  public ResponseEntity<SMSCodeResponseDto> save(@RequestBody SendSMSRequestDto requestDto) {
    SMSCodeResponseDto response = notificationService.sendSms(requestDto);
    return ResponseEntity.status(200).body(response);
  }
}
