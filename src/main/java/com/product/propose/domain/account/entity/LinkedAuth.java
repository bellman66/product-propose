package com.product.propose.domain.account.entity;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.password.PasswordUtil;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "linked_auth")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LinkedAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "confirmed")
    private LocalDateTime confirmedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public static LinkedAuth createLinkedAuth(LinkedAuthCreateForm createForm) {
        return LinkedAuth.builder()
                .accountType(createForm.getAccountType())
                .password(PasswordUtil.encode(createForm.getPassword()))
                .confirmedAt(null)
                .build();
    }

    public void checkPassword(String aPassword) {
        if (!PasswordUtil.matches(aPassword, this.password)) throw new CommonException(ErrorCode.LOGIN_PROCESS_PASSWORD_NOTMATCH);
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
