package net.ruixinglong.suntv.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import net.ruixinglong.suntv.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

@ResponseBody
@RestController
//@RequestMapping(value = "captcha")
public class CaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(value = "/captcha.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            redisUtils.set("captcha_" + httpServletRequest.getParameter("client_id"), createText, 5 * 60);
        } catch (IllegalArgumentException | IOException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return captchaChallengeAsJpeg;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Accept-Ranges", "bytes");
//        httpServletResponse.setHeader("Age", "13359793");
        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
        httpServletResponse.setHeader("Connection", "keep-alive");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setHeader("Server", "nginx");
////        httpServletResponse.setHeader("Pragma", "no-cache");Accept-Ranges: bytes
        httpServletResponse.setDateHeader("Expires", System.currentTimeMillis() + 36000000 * 24);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
        return captchaChallengeAsJpeg;
    }
}
