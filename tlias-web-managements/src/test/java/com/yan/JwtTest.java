package com.yan;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    @Test
    public void testGenerateJwt(){        //构造jwt令牌
        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        map.put("name","admin");

        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"eWFu") //指定加密算法,密钥
                .addClaims(map) //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000)) //设置令牌过期时间
                .compact();//生成令牌
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJpZCI6MSwiZXhwIjoxNzUwODIzMjU3fQ.vd5mmSVjeiW_OiJI1EuqWHyqyoA867inz9eUcuCraJE";
        Claims claims = Jwts.parser()
                .setSigningKey("eWFu")   //指定密钥
                .parseClaimsJws(token).getBody(); //解析令牌并获取自定义信息
        System.out.println(claims);
    }
}
