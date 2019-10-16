package net.ruixinglong.suntv.bean;

import lombok.Data;

@Data
public class UserRegisterBean {

    private String name;

    private String username;

    private String password;

    private String confirm_password;

    private String cellphone;
}
