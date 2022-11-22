package com.LongLQ.FoodProject.service;

import com.LongLQ.FoodProject.entity.UserEntity;

public interface LoginService {
    public boolean checkLogin(String email, String password);
    public UserEntity checkLogin(String email);

}
