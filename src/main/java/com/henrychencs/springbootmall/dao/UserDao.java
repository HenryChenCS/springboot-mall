package com.henrychencs.springbootmall.dao;

import com.henrychencs.springbootmall.dto.UserRegisterRequest;
import com.henrychencs.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
