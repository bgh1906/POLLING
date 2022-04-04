package com.polling.grpc.notification.service;

import com.google.gson.Gson;
import com.polling.grpc.ListOfNotificationRequest;
import com.polling.grpc.NotificationSmsServiceGrpc;
import com.polling.grpc.ResultStatus;
import com.polling.grpc.SMSRequest;
import com.polling.grpc.SMSResponse;
import com.polling.grpc.notification.dto.request.SendSMSApiRequestDto;
import com.polling.grpc.notification.dto.request.SendSMSRequestDto;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSmsService extends
    NotificationSmsServiceGrpc.NotificationSmsServiceImplBase {

  //    private final NotificationClient notificationClient;
  private final Gson gson;
  private final String FROM = "01065752938";
  @Value("${sms.serviceid}")
  private String serviceId;
  @Value("${sms.accesskey}")
  private String accessKey;
  @Value("${sms.secretkey}")
  private String secretKey;


  /*WebClient*/
//    public void sendSms_webClient(SendSMSRequestDto requestDto) {
//        notificationClient.sendSMS(requestDto);
//    }

  @Override
  public void sendSms(ListOfNotificationRequest request,
      StreamObserver<SMSResponse> responseObserver) {
    try {
      //protobuf를 arrayList로 매핑
      int size = request.getSmsRequestCount();
      List<SendSMSRequestDto> message = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        SMSRequest notificationRequest = request.getSmsRequest(i);
        SendSMSRequestDto requestDto = new SendSMSRequestDto(notificationRequest.getTo(),
            notificationRequest.getContent());
        message.add(requestDto);
      }
      //SMS 서버
      String randomCode = sendSmsServer(message);

      responseObserver.onNext(
          SMSResponse.newBuilder()
              .setStatus(ResultStatus.newBuilder()
                  .setCode(Status.OK.getCode().value())
                  .setMessage("Notification SUCCESS!!!")
                  .build())
              .setResult("Success Notification")
              .setRandomCode(randomCode)
              .build()
      );
      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(e);
    }

  }

  private String sendSmsServer(List<SendSMSRequestDto> messages)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
    Long time = System.currentTimeMillis();

    String randomCode = (RandomStringUtils.randomNumeric(6));

    SendSMSApiRequestDto smsRequest = new SendSMSApiRequestDto("SMS", "COMM", "82",
        FROM, randomCode, messages);
    String jsonBody = gson.toJson(smsRequest);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("x-ncp-apigw-timestamp", time.toString());
    headers.set("x-ncp-iam-access-key", this.accessKey);
    String sig = makeSignature(time);
    headers.set("x-ncp-apigw-signature-v2", sig);
    HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    /* 실제 요청 날리는 부분 주석 처리 */
//        restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+this.serviceId+"/messages"), body, SendSMSApiRequestDto.class);
    return randomCode;
  }

  private String makeSignature(Long time)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

    String space = " ";
    String newLine = "\n";
    String method = "POST";
    String url = "/sms/v2/services/" + this.serviceId + "/messages";
    String timestamp = time.toString();
    String accessKey = this.accessKey;
    String secretKey = this.secretKey;

    String message = method +
        space +
        url +
        newLine +
        timestamp +
        newLine +
        accessKey;

    SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
        "HmacSHA256");
    Mac mac = Mac.getInstance("HmacSHA256");
    mac.init(signingKey);

    byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

    return Base64.encodeBase64String(rawHmac);
  }
}
