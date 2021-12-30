package com.template.basespring.global.exception.dto.rest;

import com.template.basespring.global.exception.dto.CommonException;

public class RestReturnException extends CommonException {

    public RestReturnException(CommonException commonException) {
        super(commonException.getErrorCode(), commonException.getErrorDetailMsg());
    }
}
