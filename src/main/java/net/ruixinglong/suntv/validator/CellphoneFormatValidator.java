package net.ruixinglong.suntv.validator;

import com.aliyuncs.utils.StringUtils;
import net.ruixinglong.suntv.constraint.CellphoneFormat;
import net.ruixinglong.suntv.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CellphoneFormatValidator implements ConstraintValidator<CellphoneFormat, String> {

    private boolean required = false;

    @Autowired
    RegexUtils regexUtils;

    public void initialize(CellphoneFormat constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    //校验
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            //调用工具类对手机号码进行校验
            return regexUtils.cellphone(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return regexUtils.cellphone(value);
            }
        }
    }
}
