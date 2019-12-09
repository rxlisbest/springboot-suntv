package net.ruixinglong.suntv.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;
import net.ruixinglong.suntv.bean.FileUpTokenBean;
import net.ruixinglong.suntv.bean.QiniuBean;
import net.ruixinglong.suntv.bean.QiniuPersistentOpfsBean;
import net.ruixinglong.suntv.entity.FileEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.service.FileService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import net.ruixinglong.suntv.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URLConnection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@ResponseBody
@RestController
@RequestMapping(value = "files")
public class FileController {

    @Autowired
    QiniuBean qiniuBean;

    @Autowired
    QiniuUtils qiniuUtils;

    @Resource
    private FileService fileService;

    @GetMapping("/up-token")
    public FileUpTokenBean upToken(@Valid FileUpTokenBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        String accessKey = qiniuBean.getAccessKey();
        String secretKey = qiniuBean.getSecretKey();
        String bucket = qiniuBean.getBucket();
        String persistentPipeline = qiniuBean.getPersistentPipeline();
        String persistentNotifyUrl = qiniuBean.getPersistentNotifyUrl();
        QiniuPersistentOpfsBean qiniuPersistentOpfsBean = qiniuBean.getPersistentOpfs();
        System.out.println(qiniuPersistentOpfsBean);

        String key = qiniuUtils.getKey(request.getName());
        String persistentKey = qiniuUtils.getKeyByExt(qiniuPersistentOpfsBean.getAvthumb());
        String saveM3u8Entry = String.format("%s:%s", bucket, persistentKey);
        String avthumbM3u8Fop = String.format("avthumb/m3u8|saveas/%s", UrlSafeBase64.encodeToString(saveM3u8Entry));
        String persistentOpfs = StringUtils.join(new String[]{
                avthumbM3u8Fop
        }, ";");

        StringMap putPolicy = new StringMap();
        putPolicy.put("persistentOps", persistentOpfs);
        putPolicy.put("persistentPipeline", persistentPipeline);
        putPolicy.put("persistentNotifyUrl", persistentNotifyUrl);

        Auth auth = Auth.create(accessKey, secretKey);

        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, key, expireSeconds, putPolicy);

        request.setKey(key);
        request.setUpToken(upToken);
        return request;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public FileEntity create(@RequestBody @Valid FileEntity request, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setDomain(qiniuBean.getCdnDomain());
        request.setContent_type(URLConnection.guessContentTypeFromName(request.getKey()));
        request.setCreate_user_id((Integer) httpServletRequest.getAttribute("user_id"));
        request.setNumber(1);
        request.setTranscoding_code(request.getTranscoding_code() != null ? request.getTranscoding_code() : "");
        Integer id = fileService.create(request);
        FileEntity userEntity = fileService.findOne(id);
        return userEntity;
    }
}
