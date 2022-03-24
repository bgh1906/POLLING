package com.polling.member.service;

import com.polling.notification.SendSMSRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    private NotificationService smsService;

//    @Test
//    public void 휴대폰인증문자보내기() throws Exception{
//        //given
//        SendSMSRequestDto requestDto = new SendSMSRequestDto("01065752938", "테스트");
//        //when
//        smsService.sendSms(requestDto);
//        //then
//    }

}
