package com.template.basespring.global.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.props.kakao")
public class KakaoProps {
    private String restApiKey;
    private String javaScriptKey;
    private String redirectUrl;
    private String appAdminKey;
    private String logoutUrl;
}
