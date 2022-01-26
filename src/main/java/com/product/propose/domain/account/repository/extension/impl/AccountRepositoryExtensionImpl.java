package com.product.propose.domain.account.repository.extension.impl;

import com.product.propose.domain.account.entity.QLinkedAuth;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.product.propose.domain.account.repository.extension.AccountRepositoryExtension;

import static com.product.propose.domain.account.entity.QLinkedAuth.linkedAuth;
import static com.product.propose.domain.account.entity.aggregate.QAccount.account;

public class AccountRepositoryExtensionImpl implements AccountRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public AccountRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }


    // ===== ===== ===== ===== ===== override method ===== ===== ===== ===== =====

    @Override
    public Account findLoginByEmail(String email) {
        return queryFactory
                .selectFrom(account)
                .innerJoin(account.linkedAuthSet, linkedAuth)
                .where(
                    account.email.eq(email)
                )
                .fetchOne();
    }

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
