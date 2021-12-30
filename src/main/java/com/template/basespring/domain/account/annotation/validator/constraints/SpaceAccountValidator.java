package com.template.basespring.domain.account.annotation.validator.constraints;

import com.template.basespring.domain.account.annotation.validator.SpaceAccountCheck;
import com.template.basespring.global.annotation.constraints.BaseAccountValidator;
import com.template.basespring.global.exception.dto.CommonException;
import com.template.basespring.global.exception.dto.enums.ErrorCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// example
public class SpaceAccountValidator extends BaseAccountValidator implements ConstraintValidator<SpaceAccountCheck, Long> {

//    @Autowired
//    private SpaceService spaceService;

    @Override
    public boolean isValid(Long targetId, ConstraintValidatorContext context) {
//        try {
//            context.disableDefaultConstraintViolation();
//
//            Long accountId = getAccountId();
//            checkSpaceWithAccountId(targetId, accountId);
//        }
//        catch (CommonException commonException) {
//            context.buildConstraintViolationWithTemplate(commonException.getErrorCode().getCustomErrorCodeStr())
//                    .addConstraintViolation();
//            return false;
//        }
//        catch (Exception exception) {
//            context.buildConstraintViolationWithTemplate(ErrorCode.CONSTRAINT_PROCESS_FAIL.getCustomErrorCodeStr())
//                    .addConstraintViolation();
//            return false;
//        }
        return true;
    }

//    // Space 본인 체크
//    private void checkSpaceWithAccountId(Long spaceId, Long accountId) {
//        spaceService.checkSpaceWithAccountId(spaceId, accountId);
//    }
}
