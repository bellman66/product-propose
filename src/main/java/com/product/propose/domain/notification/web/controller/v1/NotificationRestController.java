package com.product.propose.domain.notification.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.notification.service.NotificationService;
import com.product.propose.domain.notification.service.impl.NotificationServiceImpl;
import com.product.propose.global.api.RestApiController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationRestController extends RestApiController {

    private final NotificationService notificationService;

    public NotificationRestController(ObjectMapper objectMapper, NotificationServiceImpl notificationService) {
        super(objectMapper);
        this.notificationService = notificationService;
    }
}
