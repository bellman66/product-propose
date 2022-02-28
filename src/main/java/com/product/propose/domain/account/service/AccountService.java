package com.product.propose.domain.account.service;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.web.dto.data.integration.ProfileUpdateData;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
import com.product.propose.global.data.security.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService extends UserDetailsService {

    Account getAccountInternal(String email);

    @Override
    default UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = getAccountInternal(email);
        AccountAssert.exists(account);

        return new UserAccount(account);
    }

    Account updateProfile(Long accountId, ProfileUpdateData profileUpdateData);

    Account exitAccount(Long accountId);
}
