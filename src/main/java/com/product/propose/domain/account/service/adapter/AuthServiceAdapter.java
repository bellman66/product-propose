package com.product.propose.domain.account.service.adapter;

import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.service.AuthService;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceAdapter {

    private final Map<AccountType, AuthService> authServiceGroup;

    // Key - Account Type
    public AuthServiceAdapter(@Qualifier("password") AuthService passwordAuthService,
                              @Qualifier("kakao") AuthService kakaoAuthService) {
        this.authServiceGroup = new HashMap<>(){{
            put(AccountType.PASSWORD, passwordAuthService);
            put(AccountType.KAKAO, kakaoAuthService);
        }};
    }

    public AuthService getService(AccountType targetType) {
        // Assert - 서비스 지원 유무
        CommonAssert.isTrue(authServiceGroup.containsKey(targetType), ErrorCode.NOT_SUPPORT_AUTH_SERVICE);

        return authServiceGroup.get(targetType);
    }
}
