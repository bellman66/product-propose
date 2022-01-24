package com.product.propose.domain.account.web.validator.assertion;

import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AccountAssert extends Assert {

    private static AccountRepository accountRepository;

    public AccountAssert(AccountRepository accountRepository) {
        AccountAssert.accountRepository = accountRepository;
    }

    public static void notExist(String email) {
        boolean result = accountRepository.existsByEmailAndExitedAtIsNull(email);
        if (result) throw new CommonException(ErrorCode.ACCOUNT_EXISTS);
    }

    public static void hasNotification(long accountId) {
        if (accountId != 1L) {
            throw new CommonException(ErrorCode.ACCOUNT_NOT_EQUALS);
        }
    }
}
