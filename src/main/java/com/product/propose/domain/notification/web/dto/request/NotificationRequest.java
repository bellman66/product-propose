package com.product.propose.domain.notification.web.dto.request;

import com.product.propose.domain.notification.entity.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationRequest {

    private NotificationType notificationType;
    private String title;
    private String link;
    private String message;
}
