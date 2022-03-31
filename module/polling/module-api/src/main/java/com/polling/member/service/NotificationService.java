package com.polling.member.service;

import com.google.gson.Gson;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.dto.response.SMSCodeResponseDto;
import com.polling.util.notification.SendSMSApiRequestDto;
import com.polling.util.notification.SendSMSRequestDto;
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
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

  //    private final NotificationClient notificationClient;
  private final Gson gson;

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

  /*RestTemplate*/
  public SMSCodeResponseDto sendSms(SendSMSRequestDto requestDto) {
    List<SendSMSRequestDto> messages = new ArrayList<>();
    messages.add(requestDto);
    try {
      sendSmsServer(messages);
      return new SMSCodeResponseDto("testcode");
    } catch (Exception e) {
      throw new CustomException(CustomErrorResult.FAIL_SEND_SMS);
    }
  }

  private void sendSmsServer(List<SendSMSRequestDto> messages)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
    Long time = System.currentTimeMillis();

    SendSMSApiRequestDto smsRequest = new SendSMSApiRequestDto("SMS", "COMM", "82",
        "01065752938", "테스트", messages);
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
