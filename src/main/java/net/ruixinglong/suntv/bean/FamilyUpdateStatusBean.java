package net.ruixinglong.suntv.bean;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class FamilyUpdateStatusBean {

    @NotNull(message = "request.bad_param")
    @Range(min = 0, max = 1, message = "family.status.bad_value")
    int status;
}
