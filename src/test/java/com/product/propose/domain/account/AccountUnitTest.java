package com.product.propose.domain.account;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.adapter.AuthServiceAdapter;
import com.product.propose.domain.account.service.impl.KakaoAuthServiceImpl;
import com.product.propose.domain.account.service.impl.PasswordAuthServiceImpl;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.validator.assertion.AccountAssert;
import com.product.propose.global.utils.password.PasswordUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AccountUnitTest {

    @Spy
    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private PasswordAuthServiceImpl passwordAuthService;

    @InjectMocks
    private KakaoAuthServiceImpl kakaoAuthService;

    @InjectMocks
    private AuthServiceAdapter authServiceAdapter;

    @InjectMocks
    private AccountAssert accountAssert;

    @InjectMocks
    private PasswordUtil passwordUtil;

    @Test
    @DisplayName("회원가입 Unit TEST")
    void signUpTest() {
        // GIVEN
        AccountCreateForm accountCreateForm = new AccountCreateForm("Test@gmail.com", "Test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        SignUpData signUpData = new SignUpData(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);

        Mockito.when(accountRepository.existsByEmailAndExitedAtIsNull(any(String.class))).thenReturn(false);
        Mockito.when(accountRepository.save(any(Account.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        // WHEN
        Account account = passwordAuthService.signUp(signUpData);

        // THEN
        assertThat(account)
                .isNotNull()
                .isOfAnyClassIn(Account.class);

        assertThat(account.getEmail())
                .isEqualTo("Test@gmail.com");
    }

    @Test
    @DisplayName("로그인 Unit TEST")
    void loginTest() {
        // GIVEN
        LoginRequest loginRequest = new LoginRequest(AccountType.PASSWORD, "Test@gmail.com", "PASSWORD");

        AccountCreateForm accountCreateForm = new AccountCreateForm("Test@gmail.com", "Test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        SignUpData signUpData = new SignUpData(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);
        Account returnAccount = Account.create(signUpData);

        Mockito.when(accountRepository.existsByEmailAndExitedAtIsNull(any(String.class))).thenReturn(true);
        Mockito.when(accountRepository.findAuthByEmail(any(String.class))).thenReturn(returnAccount);

        // WHEN
        Account account = passwordAuthService.login(loginRequest);

        // THEN
        assertThat(account)
                .isNotNull()
                .isOfAnyClassIn(Account.class);

        assertThat(account.getEmail())
                .isEqualTo("Test@gmail.com");
    }
}
