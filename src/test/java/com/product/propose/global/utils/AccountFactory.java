package com.product.propose.global.utils;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AccountFactory {

    private static EntityManager em;

    public AccountFactory(EntityManager em) {
        AccountFactory.em = em;
    }

    private static SignUpData getDefaultData() {
        AccountCreateForm accountCreateForm = new AccountCreateForm("default@gmail.com", "test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        return new SignUpData(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);
    }

    public static Account create() {
        Account account = Account.signUp(getDefaultData());
        setPersist(account);

        return account;
    }

    private static void setPersist(Account account) {
        em.persist(account);
        em.flush();
    }

}
