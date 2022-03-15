package com.product.propose.domain.account.service.impl;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.AuthService;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("password")
@Transactional(readOnly = true)
public class PasswordAuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    public PasswordAuthServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ===== ===== ===== ===== ===== business method ===== ===== ===== ===== =====

    @Override
    @Transactional
    public Account signUp(SignUpData aData) {
        AccountAssert.nonExist(aData.getSignUpEmail());

        Account result = Account.create(aData);
        return accountRepository.save(result);
    }

    // R
    @Override
    public Account login(LoginRequest loginRequest) {
        AccountAssert.exists(loginRequest.getEmail());

        // Find Target Account
        Account account = accountRepository.findAuthByEmail(loginRequest.getEmail());

        // Domain - authenticationToken
        account.login(loginRequest.getAccountType(), loginRequest.getPassword());
        return account;
    }
}
