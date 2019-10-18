package net.ruixinglong.suntv.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SendSmsBean {

    @NotNull(message = "request.bad_param")
    String cellphone;

    @NotNull(message = "request.bad_param")
    String captcha;
}
