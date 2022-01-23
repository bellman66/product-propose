package com.product.propose.domain.account.web.dto.response;

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
