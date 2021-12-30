package com.template.basespring.domain.account.entity;

import com.template.basespring.domain.account.dto.request.*;
import com.template.basespring.domain.account.entity.enums.AccountType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Table(name="account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Column(unique = true)
    private String phone;
    private String kakaoUserId;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] profileImage;

    private boolean kakaoAuth;
    private boolean emailRecvAgreement;
    private boolean phoneRecvAgreement;
    private boolean exited;

    private LocalDateTime exitedAt;
    private LocalDateTime createdAt;
    private LocalDateTime passwordModifiedAt;

    // password
    public static Account createAccount(SignUpRequest signUpRequest, String encodedPassword) {
        return Account.builder()
                .accountType(signUpRequest.getAccountType())
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .name(signUpRequest.getName())
                .phone(signUpRequest.getPhone())
                .profileImage(signUpRequest.getProfileImage())
                .kakaoAuth(signUpRequest.isKakaoAuthAgreement())
                .emailRecvAgreement(signUpRequest.isEmailReceiveAgreement())
                .phoneRecvAgreement(signUpRequest.isPhoneReceiveAgreement())
                .build();
    }

    // kakao
    public static Account createAccount(KakaoSignUpRequest signUpRequest, String encodedPassword) {
        return Account.builder()
                .accountType(signUpRequest.getAccountType())
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .name(signUpRequest.getName())
                .kakaoAuth(signUpRequest.isKakaoAuthAgreement())
                .emailRecvAgreement(signUpRequest.isEmailReceiveAgreement())
                .phoneRecvAgreement(signUpRequest.isPhoneReceiveAgreement())
                .kakaoUserId(signUpRequest.getKakaoUserId())
                .build();
    }

    public void completeSignUp() {
        this.exited = false;
        this.createdAt = LocalDateTime.now();
        this.passwordModifiedAt = null;
    }

    public void modifyPassword(String newPassword) {
        this.password = newPassword;
        this.passwordModifiedAt = LocalDateTime.now();
    }

    public void modifyProfile(ProfileRequest profileRequest) {
        this.phone = profileRequest.getPhone();
        this.profileImage = profileRequest.getProfileImage();
    }

    public void modifyAgreements(AgreementRequest agreementRequest) {
        this.emailRecvAgreement = agreementRequest.isEmailReceiveAgreement();
        this.phoneRecvAgreement = agreementRequest.isPhoneReceiveAgreement();
    }

    public void modifyExited(boolean exited) {
        this.exited = exited;
        if (exited) this.exitedAt = LocalDateTime.now();
    }

    public void modifyAccountInfo(AccountUpdateRequest request) {
        this.phone = request.getPhone();
        this.profileImage = request.getProfileImage();
        this.emailRecvAgreement = request.isEmailRecvAgreement();
        this.phoneRecvAgreement = request.isPhoneRecvAgreement();
    }
}
