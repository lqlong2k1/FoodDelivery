package com.cybersoft.FoodProject.service;

import com.cybersoft.FoodProject.entity.UserEntity;

public interface LoginService {
    public boolean checkLogin(String email, String password);
    public UserEntity checkLogin(String email);

}
