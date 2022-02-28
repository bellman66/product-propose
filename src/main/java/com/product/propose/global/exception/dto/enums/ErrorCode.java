package com.product.propose.global.exception.dto.enums;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum ErrorCode {

    // account, verification (1000)
    ACCOUNT_NOT_FOUND("1001", "유저가 존재하지 않습니다.",null),

    LOGIN_PROCESS_PASSWORD_NOTMATCH("1007" , "비밀번호가 일치하지 않습니다.", null),
    LOGIN_PROCESS_FAILED("1008" , "로그인에 실패하였습니다.", null),
    KAKAO_LOGIN_PROCESS_FAILED("1009" , "카카오 로그인에 실패하였습니다.", null),

    ACCOUNT_ALREADY_EXISTS("1013", "유저가 이미 존재합니다.", null),
    LINKED_AUTH_NOT_FOUND("1014", "인증 방식이 존재하지 않습니다.", null),

    // account - jwt (1200)
    JWT_TOKEN_EXPIRED("1201", "토큰이 만료되었습니다.", null),
    JWT_VERIFICATION_FAIL("1202", "로그인 인증에 실패하였습니다.", null),
    JWT_EXCEPTION_FAIL("1203", "로그인 정보가 유효하지 않습니다.", null),
    ACCESS_DENIED("1204", "접근 권한이 없습니다.", null),

    // auth
    NOT_SUPPORT_AUTH_SERVICE("1301", "지원하지 않는 인증경로입니다.", null),

    // Wiki
    WIKI_NOT_FOUND("2001", "관련된 컨텐츠를 찾지 못했습니다", null),
    ALREADY_EXISTS_WIKI("2002", "이미 동일한 컨텐츠가 존재합니다.", null),
    PRICE_RECORD_NOT_FOUND("2003", "가격 정보를 찾지 못했습니다.", null),

    // iamport (5000)
    IAMPORT_ACCESS_TOKEN_NOT_FOUND("5001", "Iamport Access Token Not Found", "/"),
    IAMPORT_CHECKOUT_CANCEL_FAIL("5002", "Iamport Checkout Cancel Fail", "/"),

    // file path (6000)
    FILE_PATH_NOT_FOUND("6001", "File Path Not Found",null),
    GOOGLE_DRIVE_FILE_PROCESS_FAIL("6002", "Failed To Add Files To Google Drive", null),
    AMAZON_S3_FILE_UPLOAD_FAIL("6003", "파일 업로드에 실패했습니다.", null),
    AMAZON_S3_FILE_DELETE_FAIL("6004", "파일 삭제에 실패했습니다.", null),

    // etc (9000)
    JSON_PROCESS_FAIL("9001", "Failed From Processing Json",null),
    WRONG_PATH("9002", "Wrong Path",null),
    CONSTRAINT_PROCESS_FAIL("9003", "정보가 서로 일치하지 않습니다.",null),

    FAILED("9999", "Unexpected Error", null);

    private static final Map<String, ErrorCode> errorMap =
            Arrays.stream(values()).collect(Collectors.toMap(ErrorCode::getCode, e->e));

    public static ErrorCode findByCode(String code) {
        return errorMap.get(code);
    }

    private String code;
    private String errorMsg;
    private String redirectUrl;

    ErrorCode(String code, String msg, String url) {
        this.code = code;
        this.errorMsg = msg;
        this.redirectUrl = url;
    }

    public String getCode() { return code; }
    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Map<String, String> getErrorMap() {
        return new HashMap<>(){{
            put("code", code);
            put("message", errorMsg);
        }};
    }

    public String getErrorString() {
        return getErrorJson().toString();
    }

    public JSONObject getErrorJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", code);
            jsonObject.put("message", errorMsg);
        }
        catch (JSONException ignore) {}
        return jsonObject;
    }

    public String getCustomErrorCodeStr() {
        return "ERRORCODE_" + this.code;
    }
}
