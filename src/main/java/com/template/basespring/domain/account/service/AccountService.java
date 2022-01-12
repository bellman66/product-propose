package com.template.basespring.domain.account.service;

import com.template.basespring.domain.account.dto.request.SignUpRequest;
import com.template.basespring.domain.account.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccountService {

    UserDetails loadUserByUsername(String email);
    Account createNewAccount(SignUpRequest signUpRequest);
    void login(Account account, String password);
}
