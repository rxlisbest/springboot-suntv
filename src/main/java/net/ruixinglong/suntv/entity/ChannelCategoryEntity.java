package net.ruixinglong.suntv.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class ChannelCategoryEntity {

    private int id;

    @NotNull(message = "channel_category.name.can_not_null")
    @Length(min = 1, max = 100, message = "channel_category.name.length")
    private String name;

    private int create_user_id;

    private Integer create_time;

    private Integer update_time;

    private int status;

    @NotNull(message = "channel_category.family_id.can_not_null")
    private int family_id;
}
