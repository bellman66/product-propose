package com.product.propose.global.annotation.constraints;

import com.product.propose.global.data.security.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseAccountValidator {

    protected Long getAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = (UserAccount) authentication.getPrincipal();
        return userAccount.getAccount().getId();
    }
}
