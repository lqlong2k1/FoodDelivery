package com.cybersoft.FoodProject.controller;

import com.cybersoft.FoodProject.jwt.JwtTokenHelper;
import com.cybersoft.FoodProject.payload.request.SignInRequest;
import com.cybersoft.FoodProject.payload.response.DataResponse;
import com.cybersoft.FoodProject.payload.response.DataTokenResponse;
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
    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    private long expireDate = 8 * 60 * 60 * 1000;
    private long expireDate_refresh = 100 * 60 * 60 * 1000;

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request) {

//        boolean isLogin = loginService.checkLogin(request.getEmail(), request.getPassword());
//        dataResponse.setSuccess(isLogin);
//        if (!isLogin) dataResponse.setDescription("Login fail! Email or password incorrect");
//        else dataResponse.setDescription("Login success");
        UsernamePasswordAuthenticationToken authenRequest =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication auth = authenticationManager.authenticate(authenRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        String token = jwtTokenHelper.generateToken(request.getEmail(),"token", expireDate);
        String refreshToken = jwtTokenHelper.generateToken(request.getEmail(),"refresh",expireDate_refresh);

        DataTokenResponse dataTokenResponse = new DataTokenResponse();
        dataTokenResponse.setToken(token);
        dataTokenResponse.setRefreshToken(refreshToken);


        String decodeToken = jwtTokenHelper.decodeToken(token);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDescription("decodeToken: "+decodeToken);
        dataResponse.setData(dataTokenResponse);

        //Khi nhập thành công trả thêm refresh token (Không hết hạn)
        //Tạo controller refresh token
        //Kiểm tra refresh token có hợp lệ hay không
        //Nếu hợp lệ thì trả về token mới
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }


}
