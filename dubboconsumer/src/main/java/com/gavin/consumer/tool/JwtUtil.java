package com.gavin.consumer.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.rtf.RTFEditorKit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 17428 on 2023/7/30.
 */
public class JwtUtil {


    public static final Long JWT_TIMEOUT = 60 * 60 * 1000L;

    //明文秘钥
    public static final String JWT_KEY="dsahfdjkhfakdhf";


    /**
     * 创建token，重载方法1
     * @param subject
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        String compact = jwtBuilder.compact();
        return compact;
    }

    /**
     * 创建token ，重载方法2
     * @param subject
     * @param expireTime
     * @return
     */
    public static String createJWT(String subject,Long expireTime) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, expireTime, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 创建token ，重载方法3
     * @param id
     * @param subject
     * @param expireTime
     * @return
     */
    public static String createJWT(String id,String subject,Long expireTime) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, expireTime, id);
        return jwtBuilder.compact();
    }


    /**
     * jwt解密:解密入参jwt
     * @param jwt
     * @return
     */
    public static Claims praseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(jwt).getBody();
        return body;
    }





//----------------------------------------------------------------------


    /**
     * 生产加密后的秘钥
     * @return
     */
    public static SecretKey generalKey() {
        byte[] decode = Base64.getDecoder().decode(JWT_KEY);
        SecretKeySpec key = new SecretKeySpec(decode, 0, decode.length, "AES");
        return key;
    }






    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    private static JwtBuilder getJwtBuilder(String subject,Long expireTime,String uuid ) {
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        Date now = new Date(System.currentTimeMillis());
        if (expireTime == null) {
            expireTime = JWT_TIMEOUT;
        }
        Date expDate = new Date(expireTime);
        return Jwts.builder().setId(uuid)
                            .setSubject(subject)//用户对象信息：JSON
                            .setIssuer("sg")   //签发者
                            .setIssuedAt(now)  //签发时间
                            .signWith(hs256,secretKey)//使用HS256对称加密算法签名, 第二个参数为秘钥
                            .setExpiration(expDate);
    }



}
