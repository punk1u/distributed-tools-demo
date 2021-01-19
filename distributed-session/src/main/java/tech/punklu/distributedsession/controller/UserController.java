package tech.punklu.distributedsession.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session){
        // 账号密码正确
        session.setAttribute("login_user",username);
        return "登录成功";
    }

    @GetMapping("/info")
    public String info(HttpSession session){
        return "当前登录的是：" + session.getAttribute("login_user");
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/loginWithToken")
    public String loginWithToken(@RequestParam String username,
                        @RequestParam String password){
        // 账号密码正确
        String key = "token_" + UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(key,username,3600, TimeUnit.SECONDS);
        return key;
    }

    @GetMapping("/infoWithToken")
    public String infoWithToken(@RequestParam String token){
        return "当前登录的是：" + stringRedisTemplate.opsForValue().get(token);
    }

    private static String JWT_KEY = "encryt_key";

    @GetMapping("/loginWithJwt")
    public String loginWithJwt(@RequestParam String username,
                                 @RequestParam String password){
        // 账号密码正确
        // 指定JWT使用的算法和对应的密钥key
        Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
        String token = JWT.create()
                // 向JWT中存入数据
                .withClaim("login_user",username)
                .withClaim("user_id",1)
                // 设置token过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm);
        return token;
    }

    @GetMapping("/infoWithJwt")
    public String infoWithJwt(@RequestParam String token){
        // 指定JWT使用的算法和对应的密钥key
        Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            // 返回该token对应的用户名
            return jwt.getClaim("login_user").asString();
        }catch (TokenExpiredException e){
            // token过期
        }catch (JWTDecodeException e){
            // 解码失败，token错误
        }

        return "error";
    }


}
