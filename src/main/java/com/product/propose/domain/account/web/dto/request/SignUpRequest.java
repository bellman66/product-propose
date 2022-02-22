package com.product.propose.domain.account.web.dto.request;

import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotNull
    private SignUpData signUpData;

    public AccountType getAccountType() {
        return signUpData.getLinkedAuthCreateForm().getAccountType();
    }
}
