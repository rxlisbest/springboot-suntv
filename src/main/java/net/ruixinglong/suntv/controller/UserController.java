package net.ruixinglong.suntv.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.ruixinglong.suntv.bean.AuthorizationBean;
import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.service.UserService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/login")
    public String login() {
        Algorithm algorithm = Algorithm.HMAC256(authorizationBean.getSecret());
        String token = JWT.create()
                .withClaim("id", 1)
                .withClaim("username", "test")
                .withClaim("name", "test")
                .sign(algorithm);
        return token;
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
}
