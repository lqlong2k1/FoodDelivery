package com.cybersoft.FoodProject.jwt;

import com.cybersoft.FoodProject.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenHelper {
    private long expireDate = 8 * 60 * 60 * 1000;
    private String strKey = "bG9uZ2xxMi1Gb29kUHJvamVjdC1DeWJlcnNvZnQtMDAx"; //chuỗi base 64

    public String generateToken(String data) {
        Date now = new Date();
        Date dataExpireDate = new Date(now.getTime() + expireDate);
//        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.ES256);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        return Jwts.builder()
                .setSubject(data) //Lưu data vào trong token theo kiểu string
                .setIssuedAt(now) //Thời gian tạo ra token
                .setExpiration(dataExpireDate) //thời gian hết hạn token;
                .signWith(secretKey, SignatureAlgorithm.HS256) // thuật toán mã hóa và secret key
                .compact(); // trả ra token đã được mã hóa
    }

    public String decodeToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parserBuilder().
                setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
