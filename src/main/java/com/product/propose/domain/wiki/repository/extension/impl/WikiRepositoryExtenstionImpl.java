package com.product.propose.domain.wiki.repository.extension.impl;

import com.product.propose.domain.wiki.repository.extension.WikiRepositoryExtenstion;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class WikiRepositoryExtenstionImpl implements WikiRepositoryExtenstion {

    private final JPAQueryFactory queryFactory;

    public WikiRepositoryExtenstionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }


    // ===== ===== ===== ===== ===== override method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
