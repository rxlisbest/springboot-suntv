package net.ruixinglong.suntv.entity;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class UserFamilyEntity {

    private int id;

    @Min(value = 1, message = "user_family.user_id.can_not_null")
    private int user_id;

    @Min(value = 1, message = "user_family.family_id.can_not_null")
    private int family_id;

    private int create_user_id;

    private Integer create_time;

    private Integer update_time;

    private int is_default;
}
