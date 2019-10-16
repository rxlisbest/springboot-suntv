package net.ruixinglong.suntv.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@ResponseBody
@RestController
@RequestMapping(value = "sms")
public class SmsController {

    @PostMapping("/create")
    public String create(HttpSession session) {
        System.out.println(session.getId());
        return session.getId();
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FrKXRqfyakYFdXbEc7M", "nAXjlAjShJ7cdhrOoBV4deQAXJvqjT");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//
//        request.putQueryParameter("PhoneNumbers", "18363857076");
//        request.putQueryParameter("SignName", "SunTV");
//        request.putQueryParameter("TemplateCode", "SMS_175570808");
//        request.putQueryParameter("TemplateParam", "{\"code\": 123456}");
//
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        return 1;
    }
}
