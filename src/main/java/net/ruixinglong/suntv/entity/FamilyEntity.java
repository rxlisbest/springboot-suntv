package net.ruixinglong.suntv.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class FamilyEntity {

    private int id;

    @NotNull(message = "family.name.can_not_null")
    @Length(min = 1, max = 100, message = "family.name.length")
    private String name;

    private int create_user_id;

    private Integer create_time;

    private Integer update_time;

    private int status;

    private int is_official;
}
