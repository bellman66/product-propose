package com.product.propose.domain.account.entity.aggregate;

import com.product.propose.domain.account.entity.LinkedAuth;
import com.product.propose.domain.account.entity.UserProfile;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    // password
    public static Account createAccount(AccountCreateForm createForm) {
        return Account.builder()
                .email(createForm.getEmail())
                .nickName(createForm.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .exitedAt(null)
                .build();
    }

    public Account signUp(LinkedAuthCreateForm linkedAuthCreateForm,
                          UserProfileCreateForm userProfileCreateForm) {
        setLinkedAuthSet(LinkedAuth.createLinkedAuth(linkedAuthCreateForm));
        setUserProfile(UserProfile.createUserProfile(userProfileCreateForm));
        return this;
    }

    private void setLinkedAuthSet(LinkedAuth linkedAuth) {
        linkedAuth.setAccount(this);
        this.linkedAuthSet.add(linkedAuth);
    }

    private void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
