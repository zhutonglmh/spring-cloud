package com.zt.demo.aopdemo.service.impl;

import com.zt.demo.aopdemo.entity.User;
import com.zt.demo.aopdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhutong
 * @date 2019/3/25 23:59
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User getUser(User user) {
        return user;
    }
}
