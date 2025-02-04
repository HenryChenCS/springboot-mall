package com.henrychencs.springbootmall.service.impl;


import com.henrychencs.springbootmall.dao.UserDao;
import com.henrychencs.springbootmall.dto.UserRegisterRequest;
import com.henrychencs.springbootmall.model.User;
import com.henrychencs.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer UserId) {
        return userDao.getUserById(UserId);
    }
}
