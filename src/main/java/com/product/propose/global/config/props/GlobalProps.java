package com.product.propose.global.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.props.global")
public class GlobalProps {
    private String url;
    private int defaultPageCnt;
}
