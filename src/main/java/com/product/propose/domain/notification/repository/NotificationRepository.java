package com.product.propose.domain.notification.repository;

import com.product.propose.domain.notification.entity.Notification;
import com.product.propose.domain.notification.repository.extension.NotificationRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryExtension {

}
