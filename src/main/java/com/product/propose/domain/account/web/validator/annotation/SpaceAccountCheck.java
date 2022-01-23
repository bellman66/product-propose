package com.product.propose.domain.account.web.validator.annotation;

import com.product.propose.domain.account.web.validator.annotation.constraints.SpaceAccountValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpaceAccountValidator.class)
@Documented
public @interface SpaceAccountCheck {

    String message() default "Error Msg";
    Class[] groups() default {};
    Class[] payload() default {};
}
