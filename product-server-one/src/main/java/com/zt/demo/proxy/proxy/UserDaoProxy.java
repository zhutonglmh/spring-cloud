package com.zt.demo.proxy.proxy;

import com.zt.demo.aopdemo.entity.User;

/**
 * @author zhutong
 * @date 2019/3/26 1:50
 */
public class UserDaoProxy implements IUserDao {
    
    /**接收保存目标对象*/
    private IUserDao target;
    
    public UserDaoProxy(IUserDao target){
        this.target=target;
    }
    
    @Override
    public void save(User user) {
        System.out.println("开始事务..."+user.toString());
        //执行目标对象的方法
        target.save(user);
        System.out.println("结束事务..."+user.toString());
    }
}
