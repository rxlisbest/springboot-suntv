package net.ruixinglong.suntv.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class FileEntity {

    private int id;

    private String domain;

    @NotNull(message = "file.key.can_not_null")
    @Length(min = 1, max = 100, message = "file.key.length")
    private String key;

    private String content_type;

    private int number;

    private String transcoding_code;

    private String source_key;

    private String source_content_type;

    private int create_user_id;

    private Integer create_time;

    private Integer update_time;
}
