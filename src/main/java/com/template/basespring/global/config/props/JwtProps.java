package com.template.basespring.global.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.props.jwt")
public class JwtProps {
    private String secretkey;
    private String issur;
    private String claimId;
}
