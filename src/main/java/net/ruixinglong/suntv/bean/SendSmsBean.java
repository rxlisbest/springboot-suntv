package net.ruixinglong.suntv.bean;

import lombok.Data;
import net.ruixinglong.suntv.constraint.CellphoneFormat;
import net.ruixinglong.suntv.provider.SendSmsBeanGroupSequenceProvider;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;

@Data
@GroupSequenceProvider(value = SendSmsBeanGroupSequenceProvider.class)
public class SendSmsBean {

    @NotNull(message = "request.bad_param")
    @CellphoneFormat(groups = cellphone.class)
    String cellphone;

    @NotNull(message = "request.bad_param")
    String captcha;

    public interface cellphone {

    }
}
