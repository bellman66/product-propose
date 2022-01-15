package com.template.basespring.domain.account.web.validator.assertion;

import com.template.basespring.global.exception.dto.CommonException;
import com.template.basespring.global.exception.dto.enums.ErrorCode;
import org.springframework.util.Assert;

public class AccountAssert extends Assert {

    public static void hasNotification(long accountId) {
        if (accountId != 1L) {
            throw new CommonException(ErrorCode.ACCOUNT_NOT_EQUALS);
        }
    }
}
