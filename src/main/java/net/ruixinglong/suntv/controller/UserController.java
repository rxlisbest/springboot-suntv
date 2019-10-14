package net.ruixinglong.suntv.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.ruixinglong.suntv.bean.AuthorizationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    AuthorizationBean authorizationBean;

    @RequestMapping("/login")
    public String index() {
        Algorithm algorithm = Algorithm.HMAC256(authorizationBean.getSecret());
        String token = JWT.create()
                .withClaim("id", 1)
                .withClaim("username", "test")
                .withClaim("name", "test")
                .sign(algorithm);
        return token;
    }
}
