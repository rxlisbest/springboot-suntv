package net.ruixinglong.suntv.bean;

import lombok.Data;
import net.ruixinglong.suntv.constraint.CellphoneFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class FileUpTokenBean {

    @NotNull(message = "request.bad_param")
    @Length(min = 1, max = 100, message = "file.name.length")
    String name;
}
