package net.ruixinglong.suntv.validator;

import com.aliyuncs.utils.StringUtils;
import net.ruixinglong.suntv.constraint.UserCellphoneUsed;
import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.service.UserService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserCellphoneUsedValidator implements ConstraintValidator<UserCellphoneUsed, String> {

    private boolean required = false;

    @Resource
    private UserService userService;

    public void initialize(UserCellphoneUsed constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    //校验
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            UserEntity userEntity = userService.findOneByCellphone(value);
            return userEntity == null ? true : false;
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                UserEntity userEntity = userService.findOneByCellphone(value);
                return userEntity == null ? true : false;
            }
        }
    }
}
