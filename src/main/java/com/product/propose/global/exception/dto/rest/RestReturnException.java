package com.product.propose.global.exception.dto.rest;

import com.product.propose.global.exception.dto.CommonException;

public class RestReturnException extends CommonException {

    public RestReturnException(CommonException commonException) {
        super(commonException.getErrorCode(), commonException.getErrorDetailMsg());
    }
}
