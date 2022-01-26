package com.product.propose.global.exception.dto.enums;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum ErrorCode {

    // account, verification (1000)
    ACCOUNT_NOT_FOUND("1001", "Account Not Found",null),
    VERIFICATION_NOT_FOUND("1002", "Verification Not Found",null),
    HOST_NOT_FOUND("1003", "Host Not Found", null),
    INSTRUCTOR_NOT_FOUND("1004", "Instructor Not Found", null),
    SCHEDULE_NOT_FOUND("1005", "Schedule Not Found", null),
    SCHEDULE_CONSULTATION_NOT_FOUND("1006", "Schedule Consultation Not Found", null),

    LOGIN_PROCESS_PASSWORD_NOTMATCH("1007" , "비밀번호가 일치하지 않습니다.", null),
    LOGIN_PROCESS_FAILED("1008" , "로그인에 실패하였습니다.", null),
    KAKAO_LOGIN_PROCESS_FAILED("1009" , "카카오 로그인에 실패하였습니다.", null),
    LOGOUT_DONE("1010", "이미 로그아웃된 계정입니다.", null),

    ACCOUNT_NOT_EQUALS("1011", "유저가 서로 일치하지 않습니다.", null),
    VERIFICATION_NOT_CORRECT("1012", "Verification Not Correct", null),
    ACCOUNT_ALREADY_EXISTS("1013", "유저 이미 존재합니다.", null),
    LINKED_AUTH_NOT_FOUND("1014", "인증 방식이 존재하지 않습니다.", null),


    // account - jwt (1200)
    JWT_TOKEN_EXPIRED("1201", "토큰이 만료되었습니다.", null),
    JWT_VERIFICATION_FAIL("1202", "로그인 인증에 실패하였습니다.", null),
    JWT_EXCEPTION_FAIL("1203", "로그인 정보가 유효하지 않습니다.", null),
    ACCESS_DENIED("1204", "접근 권한이 없습니다.", null),

    // host (1400)
    HOST_ACCOUNT_NOT_EQUALS("1401", "호스트 정보가 일치하지 않습니다.", null),

    // instructor (1500)
    INSTRUCTOR_ACCOUNT_NOT_EQUALS("1501", "강사 정보가 일치하지 않습니다.", null),

    // bookmark, tag (2000)
    BOOKMARK_NOT_FOUND("2001", "Bookmark Not Found",null),
    TAG_NOT_FOUND("2002", "Tag Not Found",null),
    TAG_REGISTER_NOT_FOUND("2003", "Tag Register Not Found",null),

    // category, zone, holiday, space, room, lesson, content (3000)
    CATEGORY_NOT_FOUND("3001", "Category Not Found",null),
    ZONE_NOT_FOUND("3002", "Zone Not Found",null),
    HOLIDAY_NOT_FOUND("3003", "Holiday Not Found",null),
    SPACE_NOT_FOUND("3004", "Space Not Found",null),
    ROOM_NOT_FOUND("3005", "Room Not Found",null),
    ROOM_KIND_NOT_FOUND("3006", "Room Kind Not Found",null),
    ROOM_SCHEDULE_NOT_FOUND("3007", "Room Schedule Not Found",null),
    LESSON_NOT_FOUND("3008", "Lesson Not Found",null),
    LESSON_KIND_NOT_FOUND("3009", "Lesson Kind Not Found", null),
    LESSON_ROOM_NOT_FOUND("3010", "Lesson Room Not Found", null),
    CONTENT_NOT_FOUND("3011", "Content Not Found", null),

    ROOM_KIND_REMAINING_NUM_ZERO("3012", "해당 수업 정원 초과입니다.",null),
    LESSON_ACCOUNT_NOT_EQUALS("3013", "강의 정보가 계정과 일치하지 않습니다.", null),
    SPACE_ACCOUNT_NOT_EQUALS("3014", "공간 정보가 계정과 일치하지 않습니다.", null),
    ROOM_ACCOUNT_NOT_EQUALS("3015", "방 정보가 계정과 일치하지 않습니다.", null),


    // reservation, payment, refund, coupon (4000)
    RESERVATION_NOT_FOUND("4001", "Reservation Not Found",null),
    PAYMENT_NOT_FOUND("4002", "Payment Not Found",null),
    REFUND_NOT_FOUND("4003", "Refund Not Found",null),
    COUPON_NOT_FOUND("4004", "Coupon Not Found",null),
    COUPON_ISSUANCE_NOT_FOUND("4005", "Coupon Issuance Not Found",null),
    COUPON_USAGE_NOT_FOUND("4006", "Coupon Usage Not Found",null),

    COUPON_ISSUANCE_EXPIRED("4007", "쿠폰 사용기간이 만료되었습니다.",null),
    COUPON_ALREADY_ISSUED("4008", "이미 발급된 쿠폰입니다.",null),
    INVALID_COUPON_CODE("4009", "Invalid Coupon Code",null),
    INVALID_COUPON_TYPE("4010", "Invalid Coupon Type",null),

    // iamport (5000)
    IAMPORT_ACCESS_TOKEN_NOT_FOUND("5001", "Iamport Access Token Not Found", "/"),
    IAMPORT_CHECKOUT_CANCEL_FAIL("5002", "Iamport Checkout Cancel Fail", "/"),

    // file path (6000)
    FILE_PATH_NOT_FOUND("6001", "File Path Not Found",null),
    GOOGLE_DRIVE_FILE_PROCESS_FAIL("6002", "Failed To Add Files To Google Drive", null),
    AMAZON_S3_FILE_UPLOAD_FAIL("6003", "파일 업로드에 실패했습니다.", null),
    AMAZON_S3_FILE_DELETE_FAIL("6004", "파일 삭제에 실패했습니다.", null),

    // board, qna, review (7000)
    BOARD_NOT_FOUND("7001", "Board Not Found",null),
    QUESTION_NOT_FOUND("7002", "Question Not Found",null),
    ANSWER_NOT_FOUND("7003", "Answer Not Found",null),
    REVIEW_NOT_FOUND("7004", "Review Not Found",null),

    // other (8000)
    DAYOFF_NOT_FOUND("8001", "휴무일이 존재하지 않습니다.", null),
    REFUND_POLICY_NOT_FOUND("8002", "환불 정책이 존재하지 않습니다.", null),
    REFUND_POLICY_NOT_VALID("8003", "환불 정책 정보와 매칭하지 않습니다..", null),
    GUIDE_NOT_FOUND("8004", "안내사항이 존재하지 않습니다.", null),

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
