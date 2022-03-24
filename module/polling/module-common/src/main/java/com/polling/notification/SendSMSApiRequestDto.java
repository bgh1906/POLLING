package com.polling.notification;

import com.polling.notification.SendSMSRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendSMSApiRequestDto {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<SendSMSRequestDto> messages;
}
