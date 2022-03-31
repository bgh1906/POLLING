package com.polling.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class NotificationServiceTest {

  @Autowired
  private NotificationService smsService;

//    @Test
//    public void 휴대폰인증문자보내기_restTemplate() throws Exception{
//        //given
//        SendSMSRequestDto requestDto = new SendSMSRequestDto("01065752938", "테스트");
//        //when
//
//        smsService.sendSms(requestDto);
//        //then
//    }

//    @Test
//    public void 휴대폰인증문자보내기_webClient() throws Exception{
//        //given
//        SendSMSRequestDto requestDto = new SendSMSRequestDto("01065752938", "테스트");
//        //when
//        smsService.sendSms_webClient(requestDto);
//        //then
//    }


}
