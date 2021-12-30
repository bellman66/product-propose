package com.template.basespring.global.api;

import lombok.Data;
import java.util.Map;

@Data
public class RestApiResponse {

    private boolean success;
    private Map<String, Object> data;

    public static RestApiResponse createJson(boolean success, Map<String, Object> data) {
        RestApiResponse restApiResponse = new RestApiResponse();
        restApiResponse.setSuccess(success);
        restApiResponse.setData(data);
        return restApiResponse;
    }
}
