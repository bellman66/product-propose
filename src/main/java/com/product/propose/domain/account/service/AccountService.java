package com.product.propose.domain.account.service;

import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import com.product.propose.domain.account.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    Account createNewAccount(SignUpRequest signUpRequest);
    void login(Account account, String password);
}
