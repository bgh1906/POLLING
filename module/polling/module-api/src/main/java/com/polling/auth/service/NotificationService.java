package com.polling.auth.service;

import com.google.gson.Gson;
import com.polling.grpc.client.dto.request.SendSMSApiRequestDto;
import com.polling.grpc.client.dto.request.SendSMSRequestDto;
import com.polling.member.dto.response.SMSCodeResponseDto;
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
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class NotificationService {

  private final Gson gson;
  private final String FROM = "01065752938";
  @Value("${sms.serviceid}")
  private String serviceId;
  @Value("${sms.accesskey}")
  private String accessKey;
  @Value("${sms.secretkey}")
  private String secretKey;

  public SMSCodeResponseDto sendSms(SendSMSRequestDto requestDto) {
    SMSCodeResponseDto responseDto = new SMSCodeResponseDto();
    try {
      List<SendSMSRequestDto> message = new ArrayList<>();
      message.add(requestDto);
      //SMS 서버
      String randomCode = sendSmsServer(message);
      responseDto.setCode(randomCode);
      return responseDto;
    } catch (Exception e) {
      throw new RuntimeException(e.getCause());
    }
  }

  private String sendSmsServer(List<SendSMSRequestDto> messages)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
    Long time = System.currentTimeMillis();

    String randomCode = (org.apache.commons.lang3.RandomStringUtils.randomNumeric(6));

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
