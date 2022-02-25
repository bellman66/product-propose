package com.product.propose.domain.account.service.impl;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.domain.account.web.dto.data.integration.ProfileUpdateData;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountInternal(String email) {
        return accountRepository.findAuthByEmail(email);
    }

    // ============================================  Update  ===================================================

    @Override
    public Account updateProfile(Long accountId, ProfileUpdateData profileUpdateData) {
        // Get & Assertion
        Account account = accountRepository.findAccountById(accountId);
        AccountAssert.isExist(account);

        // Update Account & Profile
        account.updateProfile(profileUpdateData);

        return accountRepository.save(account);
    }

    // ============================================  delete  ===================================================
}
