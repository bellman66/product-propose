package com.product.propose.example;

import com.product.propose.global.config.TestConfig;
import com.product.propose.global.factory.AccountFactory;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *   @Author : Youn
 *   @Summary : Repository Default
 *   @Memo : DataJpaTest 기본형
 **/
@DataJpaTest
@Import({TestConfig.class, AccountFactory.class})   // Bean DI를 위해 설정
public class DataJpaTestDefault {

    // repository
    @Autowired
    private AccountRepository accountRepository;

    // test util
    @Autowired
    private AccountFactory accountFactory;

    @Test
    void getAccount() { // Repository Test

        // Given
        Account account = null;

        // When
        Account result = accountRepository.save(account);

        // Then
        assertThat(result)
                .isNotNull()
                .isOfAnyClassIn(Account.class);
    }
}
