package com.template.basespring.global.config.security.setting.handler;

import com.template.basespring.global.config.security.setting.SecurityDeniedJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
class AccessDeniedCustomHandler implements AccessDeniedHandler {

    private final SecurityDeniedJsonUtil securityDeniedJsonUtil;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        securityDeniedJsonUtil.setAccessDeniedResponse(response);
    }
}
