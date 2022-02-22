package com.product.propose.domain.account.entity.aggregate;

import com.product.propose.domain.account.entity.LinkedAuth;
import com.product.propose.domain.account.entity.UserProfile;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.global.data.security.UserAccount;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.jwt.JwtUtil;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
*   @Author : Youn
*   @Summary : Account
*   @Memo : AbstractAggregateRoot
**/
@Entity
@Getter
@Table(name="account")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Account extends AbstractAggregateRoot<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "exited_at")
    private LocalDateTime exitedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LinkedAuth> linkedAuthSet = new HashSet<>();

    public static Account createAccount(AccountCreateForm createForm) {
        return Account.builder()
                .email(createForm.getEmail())
                .nickName(createForm.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .exitedAt(null)
                .build();
    }

    // Create User - SignUp
    public static Account signUp(SignUpData data) {
        Account account = createAccount(data.getAccountCreateForm());

        account.setLinkedAuthSet(LinkedAuth.createLinkedAuth(data.getLinkedAuthCreateForm()));
        account.setUserProfile(UserProfile.createUserProfile(data.getUserProfileCreateForm()));
        return account;
    }

    public void login(AccountType type, String password) {
        // Find Target Auth
        LinkedAuth targetAuth = linkedAuthSet.stream()
                .filter(auth -> auth.getAccountType() == type)
                .findFirst()
                .orElseThrow(() -> new CommonException(ErrorCode.LINKED_AUTH_NOT_FOUND));

        // Check Password
        targetAuth.checkPassword(password);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new UserAccount(this, targetAuth),
                password,
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public String getJwtToken() {
        return JwtUtil.encodeJwt(this.email);
    }

    public boolean isExited() {
        return Objects.nonNull(this.exitedAt);
    }

    private void setLinkedAuthSet(LinkedAuth linkedAuth) {
        linkedAuth.setAccount(this);
        this.linkedAuthSet.add(linkedAuth);
    }

    private void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
