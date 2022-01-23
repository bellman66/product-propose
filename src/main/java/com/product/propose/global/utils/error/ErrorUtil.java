package com.product.propose.global.utils.error;

import com.product.propose.global.exception.dto.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ErrorUtil {

    private static final String TARGET_STR = "ERRORCODE_";

    // 메세지 내에 커스텀 Code가 있는지 찾음.
    public ErrorCode findErrorCodeOnMsg(String message, ErrorCode defaultErrorCode) {
        String code = getCodeWithMessage(message);
        if (!StringUtils.hasText(code)) return defaultErrorCode;

        return ErrorCode.findByCode(code) != null ?
                ErrorCode.findByCode(code) : defaultErrorCode;
    }

    // Custom Error Code 분리
    private String getCodeWithMessage(String message) {
        Pattern pattern = Pattern.compile("ERRORCODE_[0-9]{4}");
        Matcher matcher = pattern.matcher(message);

        if(!matcher.find()) return "";
        return matcher.group().replace(TARGET_STR, "").trim();
    }
}
