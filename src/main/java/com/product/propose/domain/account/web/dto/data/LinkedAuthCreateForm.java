package com.product.propose.domain.account.web.dto.data;

import com.product.propose.domain.account.entity.enums.AccountType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LinkedAuthCreateForm {

    @NotBlank
    private final AccountType accountType;

    @NotBlank
    private final String password;
}
