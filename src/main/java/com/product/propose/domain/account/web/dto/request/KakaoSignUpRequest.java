package com.product.propose.domain.account.web.dto.request;

import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.global.utils.kakao.dto.KakaoAccountInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class KakaoSignUpRequest {

    private AccountType accountType;
    private String email;
    private String name;
    private String password;
    private boolean kakaoAuthAgreement;
    private boolean emailReceiveAgreement;
    private boolean phoneReceiveAgreement;
    private String kakaoUserId;

    public static KakaoSignUpRequest createKakaoSignUpRequest(KakaoAccountInfoDto info){
        return KakaoSignUpRequest.builder()
                .accountType(AccountType.KAKAO)
                .email(info.getEmail())
                .name(info.getNickname())
                .password(String.valueOf(info.getId()))
                .kakaoAuthAgreement(true)
                .emailReceiveAgreement(false)
                .phoneReceiveAgreement(false)
                .kakaoUserId(String.valueOf(info.getId()))
                .build();

    }

}
