package net.ruixinglong.suntv.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.ruixinglong.suntv.bean.AuthorizationBean;
import net.ruixinglong.suntv.bean.UserLoginBean;
import net.ruixinglong.suntv.bean.UserPasswordLoginBean;
import net.ruixinglong.suntv.bean.UserRegisterBean;
import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.service.UserService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import net.ruixinglong.suntv.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@ResponseBody
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    AuthorizationBean authorizationBean;

    @Resource
    private UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public UserLoginBean login(@RequestBody @Valid UserLoginBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        UserEntity userEntity = userService.findOneByCellphone(request.getCellphone());
        if (userEntity == null) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.cellphone.not_exists"));
        }
        Object smsCodeObject = redisUtils.get("sms_code_" + request.getCellphone());
        if (smsCodeObject == null) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.code.bad_value"));
        }
        String smsCodeJsonString = smsCodeObject.toString();
        JSONObject smsCodeJsonObject = JSONObject.parseObject(smsCodeJsonString);
        String smsCode = smsCodeJsonObject.getString("code");
        if (!smsCode.equals(request.getCode())) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.code.bad_value"));
        }

        Algorithm algorithm = Algorithm.HMAC256(authorizationBean.getSecret());
        String token = JWT.create()
                .withClaim("id", userEntity.getId())
                .withClaim("name", userEntity.getName())
                .withClaim("cellphone", userEntity.getCellphone())
                .sign(algorithm);
        request.setToken(token);
        return request;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserEntity register(@RequestBody @Valid UserRegisterBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Object smsCodeObject = redisUtils.get("sms_code_" + request.getCellphone());
        if (smsCodeObject == null) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.code.bad_value"));
        }
        String smsCodeJsonString = smsCodeObject.toString();
        JSONObject smsCodeJsonObject = JSONObject.parseObject(smsCodeJsonString);
        String smsCode = smsCodeJsonObject.getString("code");
        if (!smsCode.equals(request.getCode())) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.code.bad_value"));
        }
        Integer id = userService.register(request);
        UserEntity userEntity = userService.findOne(id);
        return userEntity;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public UserEntity create(@RequestBody @Valid UserEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer id = userService.create(request);
        UserEntity userEntity = userService.findOne(id);
        return userEntity;
    }

    @PutMapping("/update/{id}")
    public Integer update(@PathVariable int id, @RequestBody @Valid UserEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer rows = userService.update(id, request);
        return rows;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/password-login")
    public UserPasswordLoginBean passwordLogin(@RequestBody @Valid UserPasswordLoginBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        UserEntity userEntity = userService.findOneByCellphone(request.getCellphone());
        if (userEntity == null) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.cellphone.not_exists"));
        }

        String password = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        if (!password.equals(userEntity.getPassword())) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.password.bad_value"));
        }

        Algorithm algorithm = Algorithm.HMAC256(authorizationBean.getSecret());
        String token = JWT.create()
                .withClaim("id", userEntity.getId())
                .withClaim("name", userEntity.getName())
                .withClaim("cellphone", userEntity.getCellphone())
                .sign(algorithm);
        request.setToken(token);
        return request;
    }
}
