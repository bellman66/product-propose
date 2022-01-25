package com.product.propose.domain.account.web.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateForm {

    @NotBlank
    private String email;

    @NotBlank
    private String name;
}
