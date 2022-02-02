package com.product.propose.domain.account.service.impl;

import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.global.data.security.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ===== ===== ===== ===== ===== business method ===== ===== ===== ===== =====

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepository.findByEmail(email);
        AccountAssert.isExist(account);

        return new UserAccount(account);
    }

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====


    @Override
    @Transactional
    public Account signUpForDefault(SignUpData aData) {
        AccountAssert.nonExist(aData.getSignUpEmail());

        Account result = Account.signUp(aData);
        return accountRepository.save(result);
    }

    // R
    @Override
    public Account loginForDefault(LoginRequest loginRequest) {
        AccountAssert.isExist(loginRequest.getEmail());

        // Find Target Account
        Account account = accountRepository.findByEmail(loginRequest.getEmail());

        // Domain - authenticationToken
        account.login(loginRequest.getAccountType(), loginRequest.getPassword());
        return account;
    }
}
