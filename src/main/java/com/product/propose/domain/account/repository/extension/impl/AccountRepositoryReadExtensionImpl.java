package com.product.propose.domain.account.repository.extension.impl;

import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.response.InfoResponse;
import com.product.propose.domain.account.web.dto.response.ProfileResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.product.propose.domain.account.repository.extension.AccountRepositoryReadExtension;
import org.hibernate.annotations.QueryHints;

import java.util.List;

import static com.product.propose.domain.account.entity.QLinkedAuth.linkedAuth;
import static com.product.propose.domain.account.entity.QUserProfile.userProfile;
import static com.product.propose.domain.account.entity.aggregate.QAccount.account;

public class AccountRepositoryReadExtensionImpl implements AccountRepositoryReadExtension {

    private final JPAQueryFactory queryFactory;

    public AccountRepositoryReadExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }

    // ============================================  Read Only   ===================================================

    /**
    *   @Author : Youn
    *   @Summary : 로그인시 사용자에 대한 Simple 정보 반환
    *   @Param : accountId
    **/
    @Override
    public InfoResponse readInfoById(Long accountId) {
        return queryFactory
                .select(Projections.constructor(InfoResponse.class,
                            account.email,
                            account.nickName,
                            userProfile.userName
                        )
                )
                .from(account)
                .innerJoin(account.userProfile, userProfile)
                .where(account.id.eq(accountId))
                .setHint(QueryHints.READ_ONLY, true)
                .fetchOne();
    }

    /**
    *   @Author : Youn
    *   @Summary : 유저 프로필 정보 반환
    *   @Param : accountId
    **/
    @Override
    public ProfileResponse readProfileById(Long accountId) {
        return queryFactory
                .select(Projections.constructor(ProfileResponse.class,
                            account.email,
                            account.nickName,
                            account.createdAt,
                            userProfile.userName,
                            userProfile.phoneNumber,
                            userProfile.postCode,
                            userProfile.address,
                            userProfile.emailRecv,
                            userProfile.phoneRecv
                        )
                )
                .from(account)
                .innerJoin(account.userProfile, userProfile)
                .where(account.id.eq(accountId))
                .setHint(QueryHints.READ_ONLY, true)
                .fetchOne();
    }

    /**
     *   @Author : Youn
     *   @Summary : 프로필 정보중 연결된 Auth Type값 반환
     *   @Param : accountId
     **/
    @Override
    public List<AccountType> readAuthTypeById(Long accountId) {
        return queryFactory
                .select(linkedAuth.accountType)
                .from(linkedAuth)
                .innerJoin(linkedAuth.account, account)
                .where(account.id.eq(accountId))
                .setHint(QueryHints.READ_ONLY, true)
                .fetch();
    }


    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
