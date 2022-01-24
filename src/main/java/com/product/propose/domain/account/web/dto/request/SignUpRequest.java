package com.product.propose.domain.account.web.dto.request;

import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {

    @NotNull
    private final AccountCreateForm accountCreateForm;
    @NotNull
    private final LinkedAuthCreateForm linkedAuthCreateForm;
    @NotNull
    private final UserProfileCreateForm userProfileCreateForm;

    public String getSignUpEmail() {
        return accountCreateForm.getEmail();
    }
}
