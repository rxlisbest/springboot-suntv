package net.ruixinglong.suntv.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ChannelEntity {

    private int id;

    @Min(value = 1, message = "channel.channel_category_id.can_not_null")
    private int channel_category_id;

    @NotNull(message = "channel.name.can_not_null")
    @Length(min = 1, max = 100, message = "channel.name.length")
    private String name;

//    @Min(value = 1, message = "channel.file_id.can_not_null")
    private int file_id;

    private String url;

    private int create_user_id;

    private Integer create_time;

    private Integer update_time;
}
