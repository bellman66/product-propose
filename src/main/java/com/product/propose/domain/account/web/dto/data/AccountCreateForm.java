package com.product.propose.domain.account.web.dto.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountCreateForm {

    @NotBlank
    private final String email;

    @NotBlank
    private final String name;
}
