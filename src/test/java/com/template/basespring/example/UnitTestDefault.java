package com.template.basespring.example;

import com.template.basespring.domain.account.service.AccountService;
import com.template.basespring.domain.account.dto.request.SignUpRequest;
import com.template.basespring.domain.account.entity.Account;
import com.template.basespring.domain.account.entity.enums.AccountType;
import com.template.basespring.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;

/**
 *   @Author : Youn
 *   @Summary : 단위 테스트 Default
 *   @Memo : Mockito 를 이용한 서비스 테스트
 **/
@ExtendWith(MockitoExtension.class)
public class UnitTestDefault {

    // ex - account service
    //  AccountService 내부에 값을 주입받는 객체들을 @Mock으로 설정
    //  @Mock으로 설정된 애들이 자동으로 주입됨
    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void getAccount() {

        // Given
        //  Service 내부에 있는 메서드중 값을 반환하는 것들을 미리 정해줌 (stub)
        Long accountId = 1L; // 사전입력
        Account account = getTestAccount();
        ReflectionTestUtils.setField(account, "id", accountId); // ID setting

//        Mockito.when(accountRepository.findByIdAndExited(any(Long.class), any(boolean.class))).thenReturn(account);

        // When
//        Account result = accountService.getAccount(accountId);

        // Then
//        assertThat(result)
//                .isNotNull();
    }

    SignUpRequest getSignupForm() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setAccountType(AccountType.REGULAR);
        signUpRequest.setEmail("tiltwoone@gmail.com");
        signUpRequest.setPassword("12345");
        signUpRequest.setPasswordConfirm("12345");
        signUpRequest.setName("til21");
        signUpRequest.setKakaoAuthAgreement(false);
        signUpRequest.setEmailReceiveAgreement(false);
        signUpRequest.setPhoneReceiveAgreement(false);
        return signUpRequest;
    }

    Account getTestAccount() {
        SignUpRequest signUpRequest = getSignupForm();
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Account newAccount = Account.createAccount(signUpRequest, encodedPassword);
        newAccount.completeSignUp();
        return newAccount;
    }
}
