package com.template.basespring.domain.notification.service;

import com.template.basespring.domain.account.service.AccountService;
import com.template.basespring.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    // service
    private final AccountService accountService;

    // repository
    private final NotificationRepository notificationRepository;

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====

}
