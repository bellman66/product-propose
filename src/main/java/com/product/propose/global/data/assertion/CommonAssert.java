package com.product.propose.global.data.assertion;

import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.util.Assert;

import java.util.Objects;

public abstract class CommonAssert extends Assert {

    // assertion의 경우 무조건 참이어야되는 항목을 trigger로 설정
    // trigger 항목이 false일 경우 Throw Exception
    protected static void decideException(boolean trigger, ErrorCode targetCode) {
        if (!trigger) throw new CommonException(targetCode);
    }

    /**
    *   @Author : Youn
    *   @Summary : boolean 값 체크용
    *   @Param : boolean
    **/
    public static void isTrue(boolean expression, ErrorCode errorCode) {
        decideException(expression, errorCode);
    }

    /**
    *   @Author : Youn
    *   @Summary : Object null , empty 체크
    *   @Param : Object
    **/
    public static void exists(Object object, ErrorCode errorCode) {
        decideException(Objects.nonNull(object), errorCode);
    }
}
