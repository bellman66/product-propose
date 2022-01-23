package com.product.propose.global.event.log.dto;

import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.domain.account.entity.Account;
import com.product.propose.global.exception.dto.CommonException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExceptionEvent {

    protected String errorName;
    protected ErrorCode errorCode;
    protected String errorDetailMsg;
    protected Account account;
    protected LocalDateTime createdAt;

    public static ExceptionEvent createExceptionEvent(CommonException exception, Account account) {
        ExceptionEvent exceptionEvent = new ExceptionEvent();
        exceptionEvent.setErrorName(exception.getClass().getSimpleName());
        exceptionEvent.setErrorCode(exception.getErrorCode());
        exceptionEvent.setErrorDetailMsg(exception.getErrorDetailMsg());
        exceptionEvent.setAccount(account);
        exceptionEvent.setCreatedAt(LocalDateTime.now());

        return exceptionEvent;
    }

    public String getExceptionString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n === === === === === === === === === === === === === === === === === === === === === === \n\n");
        stringBuilder.append("Exception Title : ").append(errorName).append("\n");

        // 1. Set Account Info
        if (this.account != null) {
            stringBuilder.append("Account Id : ").append(account.getId()).append("\n");
            stringBuilder.append("Account Email : ").append(account.getEmail()).append("\n");
        }

        // 2. Set Exception
        if (this.errorCode != null) {
            stringBuilder.append("Error Code & Msg : ").append(errorCode.getCode()).append(" / ").append(errorCode.getErrorMsg()).append("\n");
        }

        // 3. Occur Date
        stringBuilder.append("createDate : ").append(createdAt.toString()).append("\n\n");

        // 4. Set Error Detail Msg
        stringBuilder.append(errorDetailMsg);
        stringBuilder.append("\n === === === === === === === === === === === === === === === === === === === === === === \n\n");

        return stringBuilder.toString();
    }
}
