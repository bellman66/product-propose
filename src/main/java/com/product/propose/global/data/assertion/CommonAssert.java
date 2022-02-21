package com.product.propose.global.data.assertion;

import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.util.Assert;

public abstract class CommonAssert extends Assert {

    // assertion의 경우 무조건 참이어야되는 항목을 trigger로 설정
    // trigger 항목이 false일 경우 Throw Exception
    protected static void decideException(boolean trigger, ErrorCode targetCode) {
        if (!trigger) throw new CommonException(targetCode);
    }

    public static void isTrue(boolean expression, ErrorCode errorCode) {
        decideException(expression, errorCode);
    }
}
