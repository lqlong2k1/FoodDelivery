package com.LongLQ.FoodProject.jwt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //cắt header và lấy token
        String token = getTokenFromHeader(request);

        if (token != null) {
            //Kiểm tra token có phải do hệ thống mình sinh ra hay không
            if (jwtTokenHelper.validateToken(token)) {
                String json = jwtTokenHelper.decodeToken(token);
                Map<String, Object> map = gson.fromJson(json, Map.class);
                System.out.println("kiem tra "+json +" - "+ map.get("type").toString());
                if (StringUtils.hasText(map.get("type").toString())
                        && !map.get("type").toString().equals("refresh")){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                }
            }
        }
        System.out.println("TOKEN: " + token);
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        //Lấy giá trị Token từ Header có key là Authorization
        String bearerToken = request.getHeader("Authorization");
        System.out.println(bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // đã kiểm tra token hợp lệ
            String token = bearerToken.substring(7); //Bearer <Token>: Chuỗi token bắt đầu từ kí tự thứ 7
            return token;
        }
        return null;
    }
}
