package com.product.propose.global.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.props.upload")
public class UploadProps {

    private String ImageApiKey;
}
