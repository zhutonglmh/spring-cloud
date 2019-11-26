package studyserverone.proxy.jdkproxy;


import java.lang.reflect.Proxy;

/**
 * @author zhutong
 * @date 2019/3/26 2:04
 */
public class Main {
    
    public static void main(String[] args) {
    
        User user = new User("111",23,"小猪佩奇");
        
        IUserDao target = new UserDao();
        JDKDynamicProxy handler = new JDKDynamicProxy(target);
        //第一个参数是指定代理类的类加载器（我们传入当前测试类的类加载器） 
        //第二个参数是代理类需要实现的接口（我们传入被代理类实现的接口，这样生成的代理类和被代理类就实现了相同的接口） 
        //第三个参数是invocation handler，用来处理方法的调用。这里传入我们自己实现的handler 
        IUserDao proxyObject = (IUserDao) Proxy.newProxyInstance(Main.class.getClassLoader(),target.getClass().getInterfaces(), handler);
//        IUserDao proxyObject = handler.getProxy();
        proxyObject.save(user);
    }
}
