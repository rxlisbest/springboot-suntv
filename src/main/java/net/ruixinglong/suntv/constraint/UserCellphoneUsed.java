package net.ruixinglong.suntv.constraint;

import net.ruixinglong.suntv.validator.UserCellphoneUsedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserCellphoneUsedValidator.class)
public @interface UserCellphoneUsed {

    //是否是必需字段，默认为true
    boolean required() default true;

    String message() default "user.create.cellphone_used";

    //下面两行是自定义注解需要添加的
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
