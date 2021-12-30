package com.template.basespring.domain.notification.repository.extension.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.template.basespring.domain.notification.repository.extension.NotificationRepositoryExtension;

public class NotificationRepositoryExtensionImpl implements NotificationRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public NotificationRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }

    // ===== ===== ===== ===== ===== override method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
