package com.template.basespring.domain.account.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountUpdateRequest {

    @NotBlank
    private String phone;

    private byte[] profileImage;

    @NotNull
    private boolean emailRecvAgreement;
    @NotNull
    private boolean phoneRecvAgreement;

}
