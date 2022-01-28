package com.product.propose.global.data.security;

import com.product.propose.domain.account.entity.LinkedAuth;
import com.product.propose.domain.account.entity.aggregate.Account;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private final Account account;

    // Token 방식의 UserAccount
    public UserAccount(Account account) {
        super(account.getEmail(), account.getEmail(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }
    public UserAccount(Account account, LinkedAuth linkedAuth) {
        super(account.getEmail(), linkedAuth.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }
}
