package net.ruixinglong.suntv.bean;

import lombok.Data;

@Data
public class ExceptionBean {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;
}
