package com.zt.demo.aopdemo.service.impl;

import com.zt.demo.aopdemo.service.UserValidator;
import org.springframework.stereotype.Service;

/**
 * @author zhutong
 * @date 2019/3/26 2:27
 */
@Service
public class UserValidatorImpl implements UserValidator {
    
    @Override
    public boolean validator() {
        return false;
    }
}
