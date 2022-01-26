package com.product.propose.domain.account.service.impl;

import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.global.data.security.UserAccount;
import com.product.propose.global.exception.dto.CommonException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder passwordEncoder;

    // repository
    private final AccountRepository accountRepository;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    // ===== ===== ===== ===== ===== business method ===== ===== ===== ===== =====

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new CommonException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        return new UserAccount(account);
    }

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====


    @Override
    @Transactional
    public Account signUpForDefault(SignUpData aData) {
        AccountAssert.isNotExist(aData.getSignUpEmail());

        Account result = Account.signUp(aData);
        return accountRepository.save(result);
    }

    // R
    @Override
    public Account login(LoginRequest loginRequest) {
        // Assertion


//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                new UserAccount(account),
//                password,
//                List.of(new SimpleGrantedAuthority("ROLE_USER"))
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return null;
    }
}
