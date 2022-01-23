package com.product.propose.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;

// 추가 설명 : 어노테이션 Valid를 위한 설정
// 어노테이션 validator 안에 Service Autowired를 위해 설정
@Configuration
public class ValidConfig {

    // 1. Validator Custom을 위한 validator 선언
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    // 2. 메서드 valid processor 설정
    // 메서드 파라미터 및 반환값에 validator설정을 세팅.
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator);
        return methodValidationPostProcessor;
    }
}
