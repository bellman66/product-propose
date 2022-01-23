package com.product.propose.global.config.security.setting.handler;

import com.product.propose.global.config.security.setting.SecurityDeniedJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
class AuthenticationFailedCustomHandler implements AuthenticationFailureHandler {

    private final SecurityDeniedJsonUtil securityDeniedJsonUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        securityDeniedJsonUtil.setAuthenticationFailedResponse(response, authenticationException);
    }
}
