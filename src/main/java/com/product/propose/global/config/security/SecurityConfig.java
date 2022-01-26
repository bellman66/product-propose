package com.product.propose.global.config.security;

import com.product.propose.global.config.security.filter.TokenAuthFilter;
import com.product.propose.global.config.security.provider.TokenAuthProvider;
import com.product.propose.global.config.security.setting.NoRedirectStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Open Url
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            PathRequest.toStaticResources().atCommonLocations(),
            new AntPathRequestMatcher("/account/login"),
            new AntPathRequestMatcher("/api-docs/**"),
            new AntPathRequestMatcher("/swagger-ui/**")
    );

    // Not Open Urls
    private static final RequestMatcher ROLE_USER_REQUIRED = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/v1/testcheck/*"),
            new AntPathRequestMatcher("/api/v1/space/add"),
            new AntPathRequestMatcher("/api/v1/lesson/add"),
            new AntPathRequestMatcher("/api/v1/account/logout"),
            new AntPathRequestMatcher("/api/v1/account/profile"),
            new AntPathRequestMatcher("/api/v1/account/profile/*"),
            new AntPathRequestMatcher("/api/v1/account/send-verify-email"),
            new AntPathRequestMatcher("/api/v1/account/exit"),
            new AntPathRequestMatcher("/api/v1/bookmark/*"),
            new AntPathRequestMatcher("/api/v1/lesson/user/*")
    );

    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            ROLE_USER_REQUIRED
    );

    private final TokenAuthProvider tokenAuthProvider;

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthProvider);
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()  // 로그인 폼 리다이렉션 방지
                .csrf().disable()       // rest 이기 떄문에 중복적인 참조
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // http - https 간의 세션 토큰이 깨지는것을 방지.

        // 주의 : 인증의 경우 TokenAuthFilter에서 처리
        http
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        http
                .authorizeRequests()
                .requestMatchers(ROLE_USER_REQUIRED).hasAnyAuthority("ROLE_USER")
                .anyRequest().permitAll();

        http
                .authenticationProvider(tokenAuthProvider)
                .addFilterBefore(restJwtAuthFilter() , UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    TokenAuthFilter restJwtAuthFilter() throws Exception {
        final TokenAuthFilter filter = new TokenAuthFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(authSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }

    @Bean
    SimpleUrlAuthenticationSuccessHandler authSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler =
                new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());
        return successHandler;
    }

    @Bean
    public FilterRegistrationBean disableAutoFilterRegister(final TokenAuthFilter filter) {
        final FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
