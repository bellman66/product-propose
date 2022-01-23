package com.product.propose.global.config.props.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.props.aws.key")
public class AwsS3KeyProps {

    private String accessKey;
    private String secretKey;
}
