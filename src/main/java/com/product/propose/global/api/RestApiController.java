package com.product.propose.global.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestApiController {

    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;

    private final ObjectMapper objectMapper;

    public RestApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected ResponseEntity<String> createFailRestResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(convertToBodyStr(data));
    }

    protected ResponseEntity<String> createRestResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(convertToBodyStr(data));
    }

    private String convertToBodyStr(Object restApiResponse) {
        try {
            return objectMapper.writeValueAsString(restApiResponse);
        }
        catch (JsonProcessingException exception) {
            throw new CommonException(ErrorCode.JSON_PROCESS_FAIL, exception);
        }
    }
}
