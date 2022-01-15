package com.template.basespring.global.utils;

import com.template.basespring.domain.account.web.dto.request.SignUpRequest;
import com.template.basespring.domain.account.entity.Account;
import com.template.basespring.domain.account.entity.enums.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountTestUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignUpRequest getSignupForm(String email) {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setAccountType(AccountType.REGULAR);
        signUpRequest.setEmail(email);
        signUpRequest.setPassword("12345");
        signUpRequest.setPasswordConfirm("12345");
        signUpRequest.setName("til21");
        signUpRequest.setKakaoAuthAgreement(false);
        signUpRequest.setEmailReceiveAgreement(false);
        signUpRequest.setPhoneReceiveAgreement(false);
        return signUpRequest;
    }

    public Account getTestAccount() {
        SignUpRequest signUpRequest = getSignupForm("default");
        return getAccount(signUpRequest);
    }

    public Account getTestAccount(String email) {
        SignUpRequest signUpRequest = getSignupForm(email);
        return getAccount(signUpRequest);
    }

    private Account getAccount(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Account newAccount = Account.createAccount(signUpRequest, encodedPassword);
        newAccount.completeSignUp();
        return newAccount;
    }
}
