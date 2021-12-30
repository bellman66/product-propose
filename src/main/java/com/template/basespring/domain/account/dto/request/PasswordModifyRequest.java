package com.template.basespring.domain.account.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordModifyRequest {

    @NotBlank
    private String newPassword;

    @NotBlank
    private String newPasswordConfirm;
}
