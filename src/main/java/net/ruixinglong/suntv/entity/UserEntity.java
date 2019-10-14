package net.ruixinglong.suntv.entity;

import lombok.Data;

@Data
public class UserEntity {

    private int id;

    private String name;

    private String username;

    private String password;

    private String cellphone;

    private Integer create_time;

    private Integer update_time;
}
