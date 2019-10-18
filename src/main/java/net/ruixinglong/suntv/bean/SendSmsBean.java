package net.ruixinglong.suntv.bean;

import lombok.Data;
import net.ruixinglong.suntv.constraint.Cellphone;
import javax.validation.constraints.NotNull;

@Data
public class SendSmsBean {

    @NotNull(message = "request.bad_param")
    @Cellphone
    String cellphone;

    @NotNull(message = "request.bad_param")
    String captcha;
}
