package com.template.basespring.domain.account.service;

import com.template.basespring.domain.account.web.dto.request.SignUpRequest;
import com.template.basespring.domain.account.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    Account createNewAccount(SignUpRequest signUpRequest);
    void login(Account account, String password);
}
