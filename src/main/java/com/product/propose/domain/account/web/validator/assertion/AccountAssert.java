package com.product.propose.domain.account.web.validator.assertion;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Component
public class AccountAssert extends CommonAssert {

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
    public static void nonExist(String email) {
        decideException(!accountRepository.existsByEmailAndExitedAtIsNull(email), ErrorCode.ACCOUNT_ALREADY_EXISTS);
    }

    /**
    *   @Author : Youn
    *   @Summary : 로그인시 주로 사용
    *   @Param : String Email
    *   @Memo : 존재할 경우 비즈니스 로직 진행
    **/
    public static void isExist(String email) {
        decideException(accountRepository.existsByEmailAndExitedAtIsNull(email), ErrorCode.ACCOUNT_NOT_FOUND);
    }

    public static void isExist(Account account) {
        decideException(Objects.nonNull(account), ErrorCode.ACCOUNT_NOT_FOUND);
    }
}
