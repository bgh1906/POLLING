package com.polling.contact.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class SaveContactRequestDto {
    private String contactStatus;
    private String contactType;
    private String title;
    private String content;
}
