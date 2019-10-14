package net.ruixinglong.suntv.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "authorization")
public class AuthorizationBean {
    private String secret;
}
