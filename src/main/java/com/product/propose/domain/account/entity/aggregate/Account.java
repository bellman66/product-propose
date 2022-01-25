package com.product.propose.domain.account.entity.aggregate;

import com.product.propose.domain.account.entity.LinkedAuth;
import com.product.propose.domain.account.entity.UserProfile;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_profile_id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LinkedAuth> linkedAuthSet = new HashSet<>();

    // Create User - SignUp
    public static Account signUp(SignUpData data) {
        AccountCreateForm accountCreateForm = data.getAccountCreateForm();
        Account account = Account.builder()
                .email(accountCreateForm.getEmail())
                .nickName(accountCreateForm.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .exitedAt(null)
                .build();

        account.setLinkedAuthSet(LinkedAuth.createLinkedAuth(data.getLinkedAuthCreateForm()));
        account.setUserProfile(UserProfile.createUserProfile(data.getUserProfileCreateForm()));
        return account;
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
