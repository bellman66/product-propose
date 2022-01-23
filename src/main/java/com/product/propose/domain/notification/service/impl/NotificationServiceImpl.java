package com.product.propose.domain.notification.service.impl;

import com.product.propose.domain.account.service.AccountService;
import com.product.propose.domain.notification.repository.NotificationRepository;
import com.product.propose.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

    // service
    private final AccountService accountService;

    // repository
    private final NotificationRepository notificationRepository;

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====

}
