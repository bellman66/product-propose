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

    /**
    *   @Author : Youn
    *   @Summary : 최초 가입시 주로 사용
    *   @Param : String TargetEmail
    *   @Memo : 기존 이메일중에 사용하는 로직이 있는 경우
    **/
    public static void isNotExist(String email) {
        if (accountRepository.existsByEmailAndExitedAtIsNull(email))
            throw new CommonException(ErrorCode.ACCOUNT_ALREADY_EXISTS);
    }

    /**
    *   @Author : Youn
    *   @Summary : 로그인시 주로 사용
    *   @Param :
    *   @Memo :
    **/
    public static void isExist() {

    }

    private static void decideException(boolean trigger, ErrorCode targetCode) {
        if (trigger) throw new CommonException(targetCode);
    }
}
