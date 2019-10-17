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
import net.ruixinglong.suntv.exception.InternalServerErrorException;
import net.ruixinglong.suntv.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@ResponseBody
@RestController
@RequestMapping(value = "sms")
public class SmsController {

    @Autowired
    RedisUtils redisUtils;

    @PostMapping("/create")
    public String create(HttpSession session) throws InternalServerErrorException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FrKXRqfyakYFdXbEc7M", "nAXjlAjShJ7cdhrOoBV4deQAXJvqjT");
        IAcsClient client = new DefaultAcsClient(profile);

        int code = (int) ((Math.random() * 9 + 1) * 100000);
        JSONObject templateParamObject = new JSONObject();
        templateParamObject.put("code", code);

        String templateParam = templateParamObject.toJSONString();

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers", "18363857076");
        request.putQueryParameter("SignName", "SunTV");
        request.putQueryParameter("TemplateCode", "SMS_175570808");
        request.putQueryParameter("TemplateParam", templateParam);

        redisUtils.set(session.getId() + "_sms_code", templateParam, 5 * 60);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return "";
        } catch (ServerException e) {
            throw new InternalServerErrorException(e.getMessage());
        } catch (ClientException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
