package net.ruixinglong.suntv.bean;

import lombok.Data;
import net.ruixinglong.suntv.constraint.CellphoneFormat;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginBean {

    @NotNull(message = "request.bad_param")
    @CellphoneFormat
    String cellphone;

    @NotNull(message = "request.bad_param")
    String code;

    String token;
}
