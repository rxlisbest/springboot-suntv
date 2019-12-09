package net.ruixinglong.suntv.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QiniuUtils {

    /**
     * 生成文件名
     * @param fileName
     * @return
     */
    public String getKey(String fileName) {
        UUID uuid = UUID.randomUUID();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return String.format("%s.%s", uuid, suffix);
    }

    /**
     * 根据后缀生成文件名
     * @param ext
     * @return
     */
    public String getKeyByExt(String ext) {
        UUID uuid = UUID.randomUUID();
        String suffix = ext;
        return String.format("%s.%s", uuid, suffix);
    }
}
