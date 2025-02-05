package com.henrychencs.springbootmall.service.impl;


import com.henrychencs.springbootmall.dao.UserDao;
import com.henrychencs.springbootmall.dto.UserLoginRequest;
import com.henrychencs.springbootmall.dto.UserRegisterRequest;
import com.henrychencs.springbootmall.model.User;
import com.henrychencs.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        //  驗證email是否存在
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null) {
            //  錯誤提示
            logger.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //  使用 MD5 生成密碼加密雜湊
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());

        userRegisterRequest.setPassword(hashedPassword);

        //  執行新增資料
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer UserId) {
        return userDao.getUserById(UserId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //  檢查 user 是否存在
        if (user == null) {
            logger.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //  使用 MD5 生成密碼加密雜湊
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //  驗證密碼
        if (user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            logger.warn("email {} 密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
