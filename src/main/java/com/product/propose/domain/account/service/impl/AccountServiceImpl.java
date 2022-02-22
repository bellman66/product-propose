package com.product.propose.domain.account.service.impl;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
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
    public Account getAccountInternal(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = getAccountInternal(email);
        AccountAssert.isExist(account);

        return new UserAccount(account);
    }

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====

}
