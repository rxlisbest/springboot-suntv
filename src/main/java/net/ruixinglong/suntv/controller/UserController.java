package net.ruixinglong.suntv.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.ruixinglong.suntv.bean.AuthorizationBean;
import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@ResponseBody
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    AuthorizationBean authorizationBean;

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String index(HttpSession session) {
        System.out.println(session.getId());
        return session.getId();
//        Algorithm algorithm = Algorithm.HMAC256(authorizationBean.getSecret());
//        String token = JWT.create()
//                .withClaim("id", 1)
//                .withClaim("username", "test")
//                .withClaim("name", "test")
//                .sign(algorithm);
//        return token;
    }

    @RequestMapping("/create")
    public Integer create(@RequestBody UserEntity request) {
        Integer id = userService.create(request);
        return id;
    }
}
