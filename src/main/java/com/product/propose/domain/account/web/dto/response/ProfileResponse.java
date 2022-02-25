package com.product.propose.domain.account.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    // Account
    private String email;
    private String nickName;
    private LocalDateTime createdAt;

    // Profile
    private String userName;
    private String phoneNumber;
    private String postCode;
    private String address;
    private boolean emailRecv;
    private boolean phoneRecv;
}
