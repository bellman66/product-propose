package com.template.basespring.domain.notification.repository;

import com.template.basespring.domain.notification.entity.Notification;
import com.template.basespring.domain.notification.repository.extension.NotificationRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryExtension {

}
