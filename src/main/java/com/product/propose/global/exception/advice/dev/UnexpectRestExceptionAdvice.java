package com.product.propose.global.exception.advice.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.global.api.RestApiControllerAdvice;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.domain.account.entity.Account;
import com.product.propose.global.annotation.CurrentAccount;
import com.product.propose.global.exception.dto.CommonException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Profile("dev")
@RestControllerAdvice(basePackages = "com.template")
@Order(Ordered.LOWEST_PRECEDENCE)
public class UnexpectRestExceptionAdvice extends RestApiControllerAdvice {

    public UnexpectRestExceptionAdvice(ObjectMapper objectMapper, ApplicationEventPublisher eventPublisher) {
        super(objectMapper, eventPublisher);
    }

    @ExceptionHandler(value= Exception.class)
    protected ResponseEntity<String> processUnexpectedException(Exception exception, @CurrentAccount Account account) {
        ErrorCode errorCode = ErrorCode.FAILED;
        CommonException commonException = new CommonException(errorCode, exception);

        // Event - Log
        sendLogEvent(commonException, account);

        // Event - Email
        sendEmailLogEvent(commonException, account);

        return createFailRestResponse(new HashMap<>() {{
            put("error", errorCode.getErrorMap());
        }});
    }
}
