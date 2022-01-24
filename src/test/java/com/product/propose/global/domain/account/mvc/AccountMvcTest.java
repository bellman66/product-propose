package com.product.propose.global.domain.account.mvc;

import com.product.propose.domain.account.entity.UserProfile;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AccountMvcTest {

    @Autowired
    private AccountService accountService;

    @Test
    void signUpTest() {
        // GIVEN
        AccountCreateForm accountCreateForm = new AccountCreateForm("Test@gmail.com", "Test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        SignUpRequest signUpRequest = new SignUpRequest(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);

        // WHEN
        Account result = accountService.signUp(signUpRequest);

        // THEN
        Assertions.assertThat(result)
                .isNotNull()
                .isOfAnyClassIn(Account.class);
    }

}
