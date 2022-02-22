package com.product.propose.domain.account;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.service.impl.PasswordAuthServiceImpl;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.mapper.AccountMapper;
import com.product.propose.domain.account.web.dto.response.InfoResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DtoTest {

    @Autowired
    private PasswordAuthServiceImpl passwordAuthService;

    @Test
    void info() {
        AccountCreateForm accountCreateForm = new AccountCreateForm("Test@gmail.com", "Test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        SignUpData signUpData = new SignUpData(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);

        Account account = passwordAuthService.signUp(signUpData);

        // WHEN
        InfoResponse infoResponse = AccountMapper.INSTANCE.accountToInfo(account);

        // THEN
        Assertions.assertThat(infoResponse)
                .isNotNull();
    }
}
