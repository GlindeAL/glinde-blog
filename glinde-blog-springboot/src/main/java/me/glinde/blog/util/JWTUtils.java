package me.glinde.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class JWTUtils {

    private static JedisUtils jedisUtils;

    @Resource
    public void setJedisUtils(JedisUtils jedisUtils) {
        JWTUtils.jedisUtils = jedisUtils;
    }

    public static String getToken(Map<String,String> map){
        String salt = RandomStringUtils.random(50);
        String key = map.get("username")+map.get("uid");
        jedisUtils.set(key,salt);
        jedisUtils.expire(key,60*60*5);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.sign(Algorithm.HMAC256(salt));

        return token;
    }

    public static String getSalt(String token){
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getClaim("username").asString();
        String uid = jwt.getClaim("uid").asString();
        String salt = jedisUtils.get(username+uid);
        return salt;
    }

    public static void verify(String token){
        String salt = getSalt(token);
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(salt)).build().verify(token);
        String username = jwt.getClaim("username").asString();
        String uid = jwt.getClaim("uid").asString();
        jedisUtils.expire(username+uid,60*60*5);
    }

    public static DecodedJWT getTokenInfo(String token){
        String salt = getSalt(token);
        return JWT.require(Algorithm.HMAC256(salt)).build().verify(token);
    }
}
