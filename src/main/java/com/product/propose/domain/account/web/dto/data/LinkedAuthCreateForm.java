package com.product.propose.domain.account.web.dto.data;

import com.product.propose.domain.account.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LinkedAuthCreateForm {

    @NotNull
    private AccountType accountType;

    @NotBlank
    private String password;
}
