package com.polling.entity.member.status;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MemberRole {
    ROLE_ADMIN("관리자"),
    ROLE_USER("회원"),
    ROLE_COMPANY("기업");

    private final String description;

    MemberRole(String description){this.description = description;};

    public static MemberRole findByMethod(String description){
        return Arrays.stream(values())
                .filter(m -> m.description.equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

