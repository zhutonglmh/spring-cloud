package studyserverone.proxy.cglib;


import studyserverone.proxy.jdkproxy.User;

/**
 * @author zhutong
 * @date 2019/3/26 2:16
 */
public class Main {
    
    public static void main(String[] args) {
        User user = new User("111",23,"小猪佩奇");
        //目标对象
        UserDao target = new UserDao();
    
        //代理对象
        UserDao proxy = (UserDao)new CglibProxyFactory(target).getProxyInstance();
        proxy.save(user);
    }
   
}
