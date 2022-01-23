package com.product.propose.global.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class RestApiController {

    private final ObjectMapper objectMapper;

    public RestApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Case 1. Fail (Spring Security Exception)

    // Case 2. Fail (Valid Error)
    //  return
    //      success: false,
    //      data: {
    //          getField(): getDefaultMessage(),
    //          ...
    //      }
    protected ResponseEntity<String> createFailRestResponse(Map<String, Object> data) {
        String responseBody = convertToBodyStr(data);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    // Case 4. Success
    //  return
    //      success: true,
    //      data: {
    //          account: {
    //              email: "hgd@gmail.com",
    //              name: "홍길동",
    //              ...
    //          },
    //          ...
    //      }
    protected ResponseEntity<String> createRestResponse(Map<String, Object> data) {
        String responseBody = convertToBodyStr(data);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    private String convertToBodyStr(Map<String, Object> restApiResponse) {
        String responseBody;

        try {
            responseBody = objectMapper.writeValueAsString(restApiResponse);
        }
        catch (JsonProcessingException exception) {
            throw new CommonException(ErrorCode.JSON_PROCESS_FAIL, exception);
        }
        return responseBody;
    }
}
