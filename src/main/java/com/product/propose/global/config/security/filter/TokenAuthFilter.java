package com.product.propose.global.config.security.filter;

import com.product.propose.global.utils.jwt.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

public class TokenAuthFilter extends AbstractAuthenticationProcessingFilter {

    private static final String BEARER = "Bearer";

    public TokenAuthFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // Seacrh Jwt Token
        final String headerToken = request.getHeader(AUTHORIZATION);
        if (!StringUtils.hasText(headerToken)) throw new BadCredentialsException("로그인이 필요한 서비스입니다.");

        // Decode Jwt
        final String confirmEmail = JwtUtil.decodeJwt(headerToken.replace(BEARER, "").trim());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(null, confirmEmail);

        return getAuthenticationManager().authenticate(authentication);
    }

    // Provider 로직 이후에 실행
    // 이후 비즈니스 로직실행을 위해 chain 연결
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
