package com.product.propose.domain.account.web.dto.data.integration;

import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpData {

    @NotNull
    private AccountCreateForm accountCreateForm;
    @NotNull
    private LinkedAuthCreateForm linkedAuthCreateForm;
    @NotNull
    private UserProfileCreateForm userProfileCreateForm;

    public String getSignUpEmail() {
        return accountCreateForm.getEmail();
    }
}
