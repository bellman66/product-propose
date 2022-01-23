package com.product.propose.global.utils.kakao;

import com.product.propose.global.config.props.KakaoProps;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.kakao.dto.KakaoAccessTokenDto;
import com.product.propose.global.utils.kakao.dto.KakaoAccountInfoDto;
import com.product.propose.global.utils.kakao.dto.KakaoAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KakaoOAuth {

    private final RestTemplate restTemplate;

    // props
    private final KakaoProps kakaoProps;

    /**
    *   @Author : Youn
    *   @Summary : Kakao Access Token
    *   @Param : KakaoAuthDto kakaoAuthDto, RedirectAttributes redirect
    *   @Memo : 리다이렉션을 통해 alert메세지를 반환하도록 설계
    **/
    public String getAccessToken(KakaoAuthDto kakaoAuthDto) {

        // 1. Request AccessToken
        ResponseEntity<KakaoAccessTokenDto> responseEntity;

        try {
            responseEntity = requestKakaoAccessToken(kakaoAuthDto);
        }
        catch (Exception exception) {
            throw new CommonException(ErrorCode.KAKAO_LOGIN_PROCESS_FAILED, exception);
        }

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            KakaoAccessTokenDto kakaoAccessTokenDto = responseEntity.getBody();

            assert kakaoAccessTokenDto != null;
            return kakaoAccessTokenDto.getAccess_token();
        }

        throw new CommonException(ErrorCode.KAKAO_LOGIN_PROCESS_FAILED, new IllegalStateException("Response Status Not 200"));
    }

    /**
    *   @Author : Youn
    *   @Summary : Kakao Account Info 가져옴
    *   @Param : accessToken
    *   @Memo : getAccessToken을 통해 먼저 accessToken 가져옴
    **/
    public KakaoAccountInfoDto getKakaoInfo(String accessToken) {

        // 1. Request Kakao Account
        ResponseEntity<KakaoAccountInfoDto> KakaoAccountInfo;

        try {
            KakaoAccountInfo = requsetKakaoAccountInfo(accessToken);
        }
        catch (Exception exception) {
            throw new CommonException(ErrorCode.KAKAO_LOGIN_PROCESS_FAILED, exception);
        }

        if (KakaoAccountInfo.getStatusCode() == HttpStatus.OK) {
            return KakaoAccountInfo.getBody();
        }

        throw new CommonException(ErrorCode.KAKAO_LOGIN_PROCESS_FAILED, new IllegalStateException("Response Status Not 200"));
    }

    /**
    * @Author: choi
    * @Summary : Kakao Log out
    * @Param : kakaoUserId
    * @Memo :
    */
    public void logoutByKakaoUserId(String kakaoUserId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + kakaoProps.getAppAdminKey());

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout?target_id_type=user_id&target_id=" + kakaoUserId, httpEntity, String.class);
    }

    // Request
    private ResponseEntity<KakaoAccessTokenDto> requestKakaoAccessToken(KakaoAuthDto kakaoAuthDto) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", kakaoProps.getRestApiKey());
        parameters.add("redirect_uri", kakaoProps.getRedirectUrl());
        parameters.add("code", kakaoAuthDto.getCode());

        return restTemplate.postForEntity("https://kauth.kakao.com/oauth/token", parameters, KakaoAccessTokenDto.class);
    }

    private ResponseEntity<KakaoAccountInfoDto> requsetKakaoAccountInfo(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, KakaoAccountInfoDto.class);
    }
}
