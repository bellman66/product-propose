package com.template.basespring.global.config.props.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.props.aws.path")
public class AwsS3PathProps {

    private String targetBucket;
    private String localUrl;

    public String getLocationUrl() {
        return this.getTargetBucket() + this.getLocalUrl();
    }
}
