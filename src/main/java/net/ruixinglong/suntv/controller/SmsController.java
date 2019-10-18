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
import net.ruixinglong.suntv.bean.SendSmsBean;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.InternalServerErrorException;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import net.ruixinglong.suntv.utils.RedisUtils;
import net.ruixinglong.suntv.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ResponseBody
@RestController
@RequestMapping(value = "sms")
public class SmsController {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RegexUtils regexUtils;

    @PostMapping("/create")
    public String create(HttpSession session, @RequestBody SendSmsBean request) throws Exception {
        System.out.println(session.getId());
        if (request.getCellphone() == null || request.getCaptcha() == null) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("bad_param"));
        }
        if (!regexUtils.cellphone(request.getCellphone())) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("sms.create.bad_cellphone_format"));
        }
        Object captchaObject = redisUtils.get(session.getId() + "_captcha");
        String captcha;
        if (captchaObject != null) {
            captcha = captchaObject.toString();
            System.out.println(captcha);
            System.out.println(request.getCaptcha());
            if (!captcha.equals(request.getCaptcha())) {
                throw new BadRequestException(LocaleMessageUtils.getMsg("sms.create.bad_captcha"));
            }
        } else {
            throw new BadRequestException(LocaleMessageUtils.getMsg("sms.create.bad_captcha"));
        }

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FrKXRqfyakYFdXbEc7M", "nAXjlAjShJ7cdhrOoBV4deQAXJvqjT");
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

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
}
