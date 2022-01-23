package com.product.propose.global.exception.advice.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.global.api.RestApiControllerAdvice;
import com.product.propose.global.utils.error.ErrorUtil;
import com.product.propose.domain.account.entity.Account;
import com.product.propose.global.annotation.CurrentAccount;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;

@Profile("dev")
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.template")
public class ExpectRestExceptionAdvice extends RestApiControllerAdvice {

    private final ErrorUtil errorUtil;

    public ExpectRestExceptionAdvice(ObjectMapper objectMapper, ApplicationEventPublisher eventPublisher, ErrorUtil errorUtil) {
        super(objectMapper, eventPublisher);
        this.errorUtil = errorUtil;
    }

    // Business Exception Catch
    @ExceptionHandler(value= CommonException.class)
    protected ResponseEntity<String> processCommonException(CommonException commonException, @CurrentAccount Account account) {
        ErrorCode errorCode = commonException.getErrorCode();

        // Event - Log
        sendLogEvent(commonException, account);

        return createFailRestResponse(new HashMap<>() {{
            put("error", errorCode.getErrorMap());
        }});
    }

    // Validation Exception Catch
    @ExceptionHandler(value= ValidationException.class)
    protected ResponseEntity<String> processValidationException(ValidationException validationException, @CurrentAccount Account account) {
        ErrorCode errorCode = errorUtil.findErrorCodeOnMsg(validationException.getMessage(), ErrorCode.CONSTRAINT_PROCESS_FAIL);
        CommonException commonException = new CommonException(errorCode, validationException);

        // Event - Log
        sendLogEvent(commonException, account);

        return createFailRestResponse(new HashMap<>() {{
            put("error", errorCode.getErrorMap());
        }});
    }
}

