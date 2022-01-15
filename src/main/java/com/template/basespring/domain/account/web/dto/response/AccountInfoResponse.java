package com.template.basespring.domain.account.web.dto.response;

import com.template.basespring.domain.account.entity.Account;
import com.template.basespring.domain.account.entity.enums.AccountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccountInfoResponse {

    private Long id;
    private AccountType accountType;
    private String email;
    private String name;
    private String phone;

    private byte[] profileImage;

    private boolean emailRecvAgreement;
    private boolean phoneRecvAgreement;

    private LocalDateTime exitedAt;
    private LocalDateTime createdAt;
    private LocalDateTime passwordModifiedAt;

    public static AccountInfoResponse getAccountProfile(Account account){
        return AccountInfoResponse.builder()
                .id(account.getId())
                .accountType(account.getAccountType())
                .email(account.getEmail())
                .name(account.getName())
                .phone(account.getPhone())
                .profileImage(account.getProfileImage())
                .emailRecvAgreement(account.isEmailRecvAgreement())
                .phoneRecvAgreement(account.isPhoneRecvAgreement())
                .exitedAt(account.getExitedAt())
                .createdAt(account.getCreatedAt())
                .passwordModifiedAt(account.getPasswordModifiedAt())
                .build();
    }
}
