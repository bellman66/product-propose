package com.template.basespring.domain.notification.web.dto.response;

import com.template.basespring.domain.notification.entity.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class NotificationResponse {

    private Long id;
    private NotificationType notificationType;
    private String title;
    private String link;
    private String message;
    private boolean checked;
    private LocalDateTime createdAt;

}
