package com.product.propose.domain.account.web.dto.data;

import com.product.propose.domain.account.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LinkedAuthCreateForm {

    @NotBlank
    private AccountType accountType;

    @NotBlank
    private String password;
}
