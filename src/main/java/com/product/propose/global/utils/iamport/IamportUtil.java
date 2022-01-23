package com.product.propose.global.utils.iamport;

import com.fasterxml.jackson.databind.JsonNode;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.iamport.dto.IamportInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class IamportUtil {

    private static final String REST_API_KEY = "0423989855496928";
    private static final String REST_API_SECRET = "68804f567423d3a0529addb5f8334b4aa6388a90372906cb85207c26e9f687fe15ec2f92f033ff23";

    private static final String ACCESS_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private static final String CANCEL_CHECKOUT_URL = "https://api.iamport.kr/payments/cancel?_token=";

    private final RestTemplate restTemplate;

    public String requestAccessToken() throws JSONException {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        JSONObject body = new JSONObject();
        body.put("imp_key", REST_API_KEY);
        body.put("imp_secret", REST_API_SECRET);

        // 1. Set Http Header, Body
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(body, headers);

        // 2. Request Access Token
        String accessToken = null;
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(ACCESS_TOKEN_URL, httpEntity, JsonNode.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode result = response.getBody();
            assert result != null;
            accessToken = result.path("response").path("access_token").textValue();
        }

        if(!StringUtils.hasText(accessToken)) throw new CommonException(ErrorCode.IAMPORT_ACCESS_TOKEN_NOT_FOUND);
        return accessToken;
    }

    public void requestCancelCheckout(String accessToken, IamportInfo iamportInfo) throws JSONException {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        JSONObject body = new JSONObject();
        body.put("imp_uid", iamportInfo.getImpUid());
        body.put("merchant_uid", iamportInfo.getMerchantUid());
        body.put("checksum", iamportInfo.getPaymentAmount());
        body.put("reason", "구매 취소");

        // 1. Set Http Header , Body
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(CANCEL_CHECKOUT_URL + accessToken, httpEntity, JsonNode.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            JsonNode result = response.getBody();

            assert result != null;
            int status = result.path("code").intValue();
            if(status != 0) {
                String errorMsg = result.path("message").textValue();
                throw new CommonException(ErrorCode.IAMPORT_CHECKOUT_CANCEL_FAIL, errorMsg);
            }
        }
    }
}
