package com.polling.contact.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindContactResponseDto {
    private Long id;
    private String contactStatus;
    private String contactType;
    private String title;
    private String content;
}
