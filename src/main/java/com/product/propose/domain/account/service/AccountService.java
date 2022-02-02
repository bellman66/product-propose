package com.product.propose.domain.account.service;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    Account signUpForDefault(SignUpData signUpData);
    Account loginForDefault(LoginRequest loginRequest);
}
