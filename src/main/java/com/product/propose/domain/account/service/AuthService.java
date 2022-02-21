package com.product.propose.domain.account.service;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;

public interface AuthService {

    Account signUp(SignUpData signUpData);
    Account login(LoginRequest loginRequest);
}
