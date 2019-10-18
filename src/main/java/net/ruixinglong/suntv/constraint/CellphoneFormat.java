package net.ruixinglong.suntv.constraint;

import net.ruixinglong.suntv.validator.CellphoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CellphoneValidator.class)
public @interface CellphoneFormat {

    //是否是必需字段，默认为true
    boolean required() default true;

    String message() default "sms.create.bad_cellphone_format";

    //下面两行是自定义注解需要添加的
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
