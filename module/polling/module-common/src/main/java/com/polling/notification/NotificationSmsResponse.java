package com.polling.notification;

import lombok.*;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationSmsResponse {

    private String requestId;
    private String statusCode;
    private String statusName;

}