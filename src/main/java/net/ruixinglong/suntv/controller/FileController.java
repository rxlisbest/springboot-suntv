package net.ruixinglong.suntv.controller;

import com.qiniu.util.Auth;
import net.ruixinglong.suntv.bean.FileUpTokenBean;
import net.ruixinglong.suntv.bean.QiniuBean;
import net.ruixinglong.suntv.entity.FileEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.service.FileService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import net.ruixinglong.suntv.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @GetMapping("/upToken")
    public String upToken(@Valid FileUpTokenBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        String accessKey = qiniuBean.getAccessKey();
        String secretKey = qiniuBean.getSecretKey();
        String bucket = qiniuBean.getBucket();
        String key = qiniuUtils.getKey(request.getName());

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        return upToken;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public FileEntity create(@RequestBody @Valid FileEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setDomain(qiniuBean.getCdnDomain());
        Integer id = fileService.create(request);
        FileEntity userEntity = fileService.findOne(id);
        return userEntity;
    }
}
