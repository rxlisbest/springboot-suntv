package net.ruixinglong.suntv.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qiniu.persistent-opfs")
public class QiniuPersistentOpfsBean {

    private String avthumb;

    private String noDomain;

    private String vb;

    private Integer t;

    
}
