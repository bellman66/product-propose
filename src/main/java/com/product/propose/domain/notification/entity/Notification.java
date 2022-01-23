package com.product.propose.domain.notification.entity;

import com.product.propose.domain.notification.entity.enums.NotificationType;
import com.product.propose.domain.notification.web.dto.request.NotificationRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "notification_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private String title;
    private String link;
    private String message;

    private boolean checked;

    private LocalDateTime createdAt;

    public static Notification createNotification(NotificationRequest notificationRequest) {
        return Notification.builder()
                .notificationType(notificationRequest.getNotificationType())
                .title(notificationRequest.getTitle())
                .link(notificationRequest.getLink())
                .message(notificationRequest.getMessage())
                .checked(false)
                .build();
    }

    public void completeCreateNotification() {
        this.createdAt = LocalDateTime.now();
    }

    public void modifyChecked(boolean checked) {
        this.checked = checked;
    }
}
