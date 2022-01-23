package com.product.propose.global.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.Account;
import com.product.propose.global.event.log.dto.ExceptionEvent;
import com.product.propose.global.exception.dto.CommonException;
import org.springframework.context.ApplicationEventPublisher;

public class RestApiControllerAdvice extends RestApiController {

    private final ApplicationEventPublisher eventPublisher;

    public RestApiControllerAdvice(ObjectMapper objectMapper, ApplicationEventPublisher eventPublisher) {
        super(objectMapper);
        this.eventPublisher = eventPublisher;
    }

    protected void sendLogEvent(CommonException aException, Account account) {
        eventPublisher.publishEvent(ExceptionEvent.createExceptionEvent(aException, account));
    }

    // TODO : Error Email Logic 미구현 - Unexpected Logic
    protected void sendEmailLogEvent(CommonException aException, Account account) {

    }
}
