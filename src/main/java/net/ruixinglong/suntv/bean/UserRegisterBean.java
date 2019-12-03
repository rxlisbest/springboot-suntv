package net.ruixinglong.suntv.bean;

import lombok.Data;
import net.ruixinglong.suntv.constraint.CellphoneFormat;

import javax.validation.constraints.NotNull;

@Data
public class UserRegisterBean {

    @NotNull(message = "request.bad_param")
    String name;

    @NotNull(message = "request.bad_param")
    @CellphoneFormat
    String cellphone;

    @NotNull(message = "request.bad_param")
    String code;

    String token;
}
