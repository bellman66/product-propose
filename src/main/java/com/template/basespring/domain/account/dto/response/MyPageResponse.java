package com.template.basespring.domain.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyPageResponse {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private byte[] profileImage;

    Long couponCount;
    Long bookmarkCount;
    Long notificationCount;
}
