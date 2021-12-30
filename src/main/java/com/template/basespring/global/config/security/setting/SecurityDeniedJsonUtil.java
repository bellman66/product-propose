package com.template.basespring.global.config.security.setting;

import com.template.basespring.global.exception.dto.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class SecurityDeniedJsonUtil {

    public void setAccessDeniedResponse(HttpServletResponse response) throws IOException {
        setDefaultErrorResponse(response, HttpServletResponse.SC_FORBIDDEN);
        createRestResponse(response, ErrorCode.ACCESS_DENIED.getErrorString());
    }

    public void setAuthenticationFailedResponse(HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        setDefaultErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
        createRestResponse(response, authenticationException.getMessage());
    }

    // Inner Method

    private void setDefaultErrorResponse(HttpServletResponse response, int status) {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
    }

    private void createRestResponse(HttpServletResponse servletResponse, String message) throws IOException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("success", false);
            jsonObject.put("data", new JSONObject(message));

        } catch (JSONException ignored) {
        } finally {
            PrintWriter writer = servletResponse.getWriter();
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();
        }
    }
}
