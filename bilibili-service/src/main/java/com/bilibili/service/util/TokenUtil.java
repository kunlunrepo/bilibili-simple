package com.bilibili.service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bilibili.domain.exception.ConditionException;

import java.util.Calendar;
import java.util.Date;

/**
 * description : token工具类
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:25
 */
public class TokenUtil {

    // 生成token
    public static String generateToken(Long userId) throws Exception {
        // 算法
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        // 过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);
        // jwt生成token
        return JWT.create()
                .withKeyId(String.valueOf(userId))
                .withIssuer("bilibili")
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    // 校验token
    public static Long verifyToken(String token) {

        try {
            //
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException e) {
            throw new ConditionException("555","token过期！");
        } catch (Exception e) {
            throw new ConditionException("非法用户token！");
        }

    }
}
