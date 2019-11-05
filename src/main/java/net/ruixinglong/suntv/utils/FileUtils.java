package net.ruixinglong.suntv.utils;

import org.springframework.stereotype.Component;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.UUID;

@Component
public class FileUtils {

    private String PREFIX_VIDEO = "video/";

    private String getMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileName);
        return type;
    }

    public boolean isVedioFile(String fileName) {
        String mimeType = getMimeType(fileName);
        if (fileName.length() > 0 && mimeType.contains(PREFIX_VIDEO)) {
            return true;
        }
        return false;
    }
}
