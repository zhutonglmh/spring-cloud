package studyserverone.proxy.proxy;

import com.zt.demo.aopdemo.entity.User;

/**
 * @author zhutong
 * @date 2019/3/26 1:52
 */
public class Main {
    
    public static void main(String[] args) {
    
        User user = new User("111",23,"小猪佩奇");
        UserDao target = new UserDao();
        
        //代理对象,把目标对象传给代理对象,建立代理关系
        IUserDao proxy = new UserDaoProxy(target);
        
        
        //执行的是代理的方法
        proxy.save(user);
        
        
    }
}
