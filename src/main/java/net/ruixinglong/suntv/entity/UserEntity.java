package net.ruixinglong.suntv.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.ruixinglong.suntv.constraint.CellphoneFormat;
import net.ruixinglong.suntv.provider.UserEntityGroupSequenceProvider;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
//@GroupSequenceProvider(value = UserEntityGroupSequenceProvider.class)
public class UserEntity {

    private int id;

    @NotNull(message = "user.name.can_not_null")
    private String name;

    @NotNull(message = "user.username.can_not_null")
    @Length(min = 6, max = 20, message = "user.username.length")
    private String username;

    private String password;

    @CellphoneFormat
    private String cellphone;

    private Integer create_time;

    private Integer update_time;
}
