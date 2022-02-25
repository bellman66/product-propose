package com.product.propose.domain.account.repository;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.extension.AccountRepositoryReadExtension;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import javax.persistence.QueryHint;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

/**
 *   @Author : Youn
 *   @Summary : Account 반환
 *   @Memo : Update , Delete를 위한 entity 반환
 **/
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryReadExtension {

    /**
    *   @Author : Youn
    *   @Summary : Assertion Query
    **/
    boolean existsByEmailAndExitedAtIsNull(String email);

    /**
    *   @Author : Youn
    *   @Summary : Security 인증용
    **/
    @EntityGraph(attributePaths = {"linkedAuthSet"})
    @QueryHints(value = @QueryHint(name = READ_ONLY, value = "true"))
    Account findAuthByEmail(String email);

    @EntityGraph(attributePaths = {"userProfile", "linkedAuthSet"})
    Account findAccountById(Long id);
}
