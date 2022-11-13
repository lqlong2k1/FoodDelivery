package com.cybersoft.FoodProject.security;

import com.cybersoft.FoodProject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomeAuthenProvider implements AuthenticationProvider {
    @Autowired
    LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Xử lý logic code đăng nhập thành công hay thất bại\
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Check data input: Email: " + email + " || Password: " + password);

        //query database:
        boolean isLoginSuccess = loginService.checkLogin(email, password);
        System.out.println("Kiem tra trang thai login: "+isLoginSuccess);
        if (isLoginSuccess) {
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
