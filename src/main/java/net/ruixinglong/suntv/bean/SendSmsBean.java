package net.ruixinglong.suntv.bean;

import lombok.Data;
import net.ruixinglong.suntv.constraint.CellphoneFormat;

import javax.validation.constraints.NotNull;

@Data
public class SendSmsBean {

    @NotNull(message = "request.bad_param")
    @CellphoneFormat
    String cellphone;

    @NotNull(message = "request.bad_param")
    String captcha;
}
