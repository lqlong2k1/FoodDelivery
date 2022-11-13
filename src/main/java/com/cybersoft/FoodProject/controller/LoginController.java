package com.cybersoft.FoodProject.controller;

import com.cybersoft.FoodProject.payload.request.SignInRequest;
import com.cybersoft.FoodProject.payload.response.DataResponse;
import com.cybersoft.FoodProject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin // cho phép những domain khác với domain của api truy cập vào
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
//        boolean isLogin = loginService.checkLogin(request.getEmail(), request.getPassword());
//        dataResponse.setSuccess(isLogin);
//        if (!isLogin) dataResponse.setDescription("Login fail! Email or password incorrect");
//        else dataResponse.setDescription("Login success");
        UsernamePasswordAuthenticationToken authenRequest =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication auth = authenticationManager.authenticate(authenRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        dataResponse.setDescription("");
        dataResponse.setData("");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
