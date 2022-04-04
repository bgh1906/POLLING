package com.polling.grpc.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.polling.grpc.*;
import com.polling.grpc.client.dto.request.SendSMSRequestDto;
import com.polling.member.dto.response.SMSCodeResponseDto;
import io.grpc.Deadline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/api/notify")
public class NotificationStub extends AbstractStub {

  private NotificationServiceGrpc.NotificationServiceFutureStub stub() {
    return NotificationServiceGrpc.newFutureStub(channel())
        .withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS));
  }

  @PostMapping("/sms")
  public ResponseEntity<SMSCodeResponseDto> sendEventTest(@RequestBody SendSMSRequestDto requestDto) {
    NotificationServiceGrpc.NotificationServiceFutureStub stub = stub();
    NotificationRequest request = NotificationRequest.newBuilder()
        .setTo(requestDto.getTo())
        .setContent(requestDto.getContent())
        .build();
    ListOfNotificationRequest req = ListOfNotificationRequest.newBuilder()
            .addNotificationRequest(request).build();


    ListenableFuture<NotificationResponse> responseFuture = stub.sendNotification(req);
    SMSCodeResponseDto responseDto = new SMSCodeResponseDto();
    try {
      NotificationResponse response = responseFuture.get(5000, TimeUnit.MILLISECONDS); //5 sec
      log.info("response : {}", response.getRandomCode());
      responseDto.setCode(response.getRandomCode());

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return ResponseEntity.status(200).body(responseDto);
  }
}
