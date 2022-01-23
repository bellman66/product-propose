package com.product.propose.global.config.security.provider;

import com.product.propose.domain.account.service.AccountService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    private final AccountService accountService;

    public TokenAuthProvider(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final String targetEmail = String.valueOf(authentication.getCredentials());
        return accountService.loadUserByUsername(targetEmail);
    }

    // retrieveUser 이후 로직 실행
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }
}
