package net.ruixinglong.suntv.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuBean {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String cdnDomain;

    private  String persistentPipeline;

    private String persistentNotifyUrl;

    private QiniuPersistentOpfsBean persistentOpfs;
}
