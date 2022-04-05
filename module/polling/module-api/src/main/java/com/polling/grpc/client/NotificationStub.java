package com.polling.grpc.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.polling.aop.annotation.Trace;
import com.polling.grpc.ListOfNotificationRequest;
import com.polling.grpc.MailRequest;
import com.polling.grpc.MailResponse;
import com.polling.grpc.NotificationMailServiceGrpc;
import com.polling.grpc.NotificationSmsServiceGrpc;
import com.polling.grpc.SMSRequest;
import com.polling.grpc.SMSResponse;
import com.polling.grpc.client.dto.request.SendMailRequestDto;
import com.polling.grpc.client.dto.request.SendSMSRequestDto;
import com.polling.member.dto.response.SMSCodeResponseDto;
import io.grpc.Deadline;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/notify")
public class NotificationStub extends AbstractStub {

  @Trace
  @PostMapping("/deprecated/sms/")
  public ResponseEntity<SMSCodeResponseDto> sendEventTest(
      @RequestBody SendSMSRequestDto requestDto) {
    NotificationSmsServiceGrpc.NotificationSmsServiceFutureStub stub = smsStub();
    SMSRequest request = SMSRequest.newBuilder()
        .setTo(requestDto.getTo())
        .setContent(requestDto.getContent())
        .build();
    ListOfNotificationRequest req = ListOfNotificationRequest.newBuilder()
        .addSmsRequest(request).build();
    ListenableFuture<SMSResponse> responseFuture = stub.sendSms(req);
    SMSCodeResponseDto responseDto = new SMSCodeResponseDto();
    try {
      SMSResponse response = responseFuture.get(5000, TimeUnit.MILLISECONDS); //5 sec
      responseDto.setCode(response.getRandomCode());

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return ResponseEntity.status(200).body(responseDto);
  }

  @Trace
  @PostMapping("/mail")
  public String sendReward(@RequestBody SendMailRequestDto requestDto) {

    NotificationMailServiceGrpc.NotificationMailServiceFutureStub stub = mailStub();

    MailRequest request =
        MailRequest.newBuilder()
            .setEmail(requestDto.getUserEmail())
            .setGiftType(requestDto.getGiftType())
            .build();

    ListenableFuture<MailResponse> responseFuture = stub.sendReward(request);

    try {
      MailResponse response = responseFuture.get(300000, TimeUnit.MILLISECONDS); //300 sec

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return "OK";
  }

  private NotificationSmsServiceGrpc.NotificationSmsServiceFutureStub smsStub() {
    return NotificationSmsServiceGrpc.newFutureStub(channel())
        .withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS));
  }

  private NotificationMailServiceGrpc.NotificationMailServiceFutureStub mailStub() {
    return NotificationMailServiceGrpc.newFutureStub(channel())
        .withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS));
  }
}
