package com.template.basespring.domain.account.service.impl;

import com.template.basespring.domain.account.dto.request.SignUpRequest;
import com.template.basespring.domain.account.entity.Account;
import com.template.basespring.domain.account.repository.AccountRepository;
import com.template.basespring.domain.account.service.AccountService;
import com.template.basespring.global.data.security.UserAccount;
import com.template.basespring.global.exception.dto.CommonException;
import com.template.basespring.global.exception.dto.enums.ErrorCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder passwordEncoder;

    // repository
    private final AccountRepository accountRepository;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    // ===== ===== ===== ===== ===== business method ===== ===== ===== ===== =====

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new CommonException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        return new UserAccount(account);
    }

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====

    // C
    @Transactional
    public Account createNewAccount(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Account newAccount = Account.createAccount(signUpRequest, encodedPassword);

        newAccount.completeSignUp();
        return accountRepository.save(newAccount);
    }

    // R
    public void login(Account account, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                password,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
