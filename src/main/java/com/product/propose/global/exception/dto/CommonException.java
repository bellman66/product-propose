package com.product.propose.global.exception.dto;

import com.product.propose.global.exception.dto.enums.ErrorCode;
import lombok.Getter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
public class CommonException extends RuntimeException {

    private ErrorCode errorCode;
    private String errorDetailMsg;

    public CommonException(ErrorCode errorcode) {
        this.errorCode = errorcode;
        this.errorDetailMsg = getStackTraceMsg(this);
    }

    public CommonException(ErrorCode errorcode, String errorDetailMsg) {
        this.errorCode = errorcode;
        this.errorDetailMsg = errorDetailMsg;
    }

    public CommonException(ErrorCode errorcode, Exception exception) {
        this.errorCode = errorcode;
        this.errorDetailMsg = getStackTraceMsg(exception);
    }

    protected String getStackTraceMsg(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
