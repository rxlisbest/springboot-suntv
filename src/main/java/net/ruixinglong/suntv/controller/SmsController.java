package net.ruixinglong.suntv.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import net.ruixinglong.suntv.bean.AliyunBean;
import net.ruixinglong.suntv.bean.SendSmsBean;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.InternalServerErrorException;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import net.ruixinglong.suntv.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@ResponseBody
@RestController
@RequestMapping(value = "sms")
public class SmsController {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    AliyunBean aliyunBean;

    @PostMapping("/create")
    public String create(HttpSession session, @RequestBody @Valid SendSmsBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }

        Object captchaObject = redisUtils.get(session.getId() + "_captcha");
        String captcha;
        if (captchaObject != null) {
            captcha = captchaObject.toString();
            if (!captcha.equals(request.getCaptcha())) {
                throw new BadRequestException(LocaleMessageUtils.getMsg("sms.create.bad_captcha"));
            }
        } else {
            throw new BadRequestException(LocaleMessageUtils.getMsg("sms.create.bad_captcha"));
        }

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunBean.getAccessKeyId(), aliyunBean.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        int code = (int) ((Math.random() * 9 + 1) * 100000);
        JSONObject templateParamObject = new JSONObject();
        templateParamObject.put("code", code);

        String templateParam = templateParamObject.toJSONString();

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysDomain("dysmsapi.aliyuncs.com");
        commonRequest.setSysVersion("2017-05-25");
        commonRequest.setSysAction("SendSms");
        commonRequest.putQueryParameter("RegionId", "cn-hangzhou");

        commonRequest.putQueryParameter("PhoneNumbers", request.getCellphone());
        commonRequest.putQueryParameter("SignName", "SunTV");
        commonRequest.putQueryParameter("TemplateCode", "SMS_175570808");
        commonRequest.putQueryParameter("TemplateParam", templateParam);

        try {
            CommonResponse response = client.getCommonResponse(commonRequest);
            redisUtils.set(session.getId() + "_sms_code", templateParam, 5 * 60); // 存储短信验证码
            redisUtils.setRemove(session.getId() + "_captcha"); // 清除图形验证码
            return response.getData();
        } catch (ServerException e) {
            throw new InternalServerErrorException(e.getMessage());
        } catch (ClientException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
