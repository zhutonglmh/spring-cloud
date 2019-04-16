package com.zt.demo;

import com.zt.demo.aopdemo.entity.User;
import com.zt.demo.aopdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhutong
 * @date 2019/3/25 23:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void test() {
    
        User user = new User("111",23,"小猪佩奇");
        userService.getUser(user);
    }
}
