package com.LongLQ.FoodProject.security;

import com.LongLQ.FoodProject.entity.UserEntity;
import com.LongLQ.FoodProject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomeAuthenProvider implements AuthenticationProvider {
    @Autowired
    LoginService loginService;

    //    @Autowired
//  @Qualifier("ten @bean"):  chỉ định tên bean mà mình muốn lấy trên container
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Xử lý logic code đăng nhập thành công hay thất bại\
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Check data input: Email: " + email + " || Password: " + password);

        //query database:
//        boolean isLoginSuccess = loginService.checkLogin(email, password);
//        System.out.println("Kiem tra trang thai login: "+isLoginSuccess);
//        if (isLoginSuccess) {
//            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
//        }
//        return null;
//    }
        UserEntity userEntity = loginService.checkLogin(email);

        if (userEntity != null) {
            boolean isMatchPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if (isMatchPassword) {
                return new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
