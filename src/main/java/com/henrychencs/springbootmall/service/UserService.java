package com.henrychencs.springbootmall.service;

import com.henrychencs.springbootmall.dto.UserLoginRequest;
import com.henrychencs.springbootmall.dto.UserRegisterRequest;
import com.henrychencs.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User login(UserLoginRequest userLoginRequest);
}
