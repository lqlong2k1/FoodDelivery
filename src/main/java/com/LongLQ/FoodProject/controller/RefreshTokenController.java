package com.LongLQ.FoodProject.controller;

import com.LongLQ.FoodProject.jwt.JwtTokenHelper;
import com.LongLQ.FoodProject.payload.response.DataResponse;
import com.LongLQ.FoodProject.payload.response.DataTokenResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("refresh-token")
public class RefreshTokenController {
    @Autowired
    JwtTokenHelper jwtTokenHelper;

    private Gson gson = new Gson();
    private long expireDate = 8 * 60 * 60 * 1000;
    private long expireDate_refresh = 100 * 60 * 60 * 1000;

    @PostMapping()
    public ResponseEntity<?> index(@RequestParam("token") String token){
        DataResponse dataResponse = new DataResponse();
        if(jwtTokenHelper.validateToken(token)){
            String json = jwtTokenHelper.decodeToken(token);
            Map<String, Object> map = gson.fromJson(json, Map.class);
            if(StringUtils.hasText(map.get("type").toString())
                    && map.get("type").toString().equals("refresh")){

                String tokenAuthen = jwtTokenHelper.generateToken(map.get("username").toString(),"authen",expireDate);
                String refeshToken = jwtTokenHelper.generateToken(map.get("username").toString(),"refresh",expireDate_refresh);

                DataTokenResponse dataTokenResponse = new DataTokenResponse();
                dataTokenResponse.setToken(tokenAuthen);
                dataTokenResponse.setRefreshToken(refeshToken);

                dataResponse.setStatus(HttpStatus.OK.value());
                dataResponse.setSuccess(true);
                dataResponse.setDescription("");
                dataResponse.setData(dataTokenResponse);
            }
        }else{
            dataResponse.setStatus(HttpStatus.OK.value());
            dataResponse.setSuccess(true);
            dataResponse.setDescription("");
            dataResponse.setData("");
        }


        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
