package com.LongLQ.FoodProject.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {
    private String strKey = "bG9uZ2xxMi1Gb29kUHJvamVjdC1DeWJlcnNvZnQtMDAx"; //chuỗi base 64
    private Gson gson = new Gson();

    public String generateToken(String data, String type, long expireDate) {
        Date now = new Date();
        Date dataExpireDate = new Date(now.getTime() + expireDate);
//        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.ES256);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        Map<String, Object> subjectData = new HashMap<>();
        subjectData.put("username", data);
        subjectData.put("type", type);

        String json = gson.toJson(subjectData);
        return Jwts.builder()
                .setSubject(json) //Lưu data vào trong token theo kiểu string
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

    public boolean validateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        boolean isSuccess = false;
        try {
            Jwts.parserBuilder().
                    setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            isSuccess = true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }

        return isSuccess;
    }
}
