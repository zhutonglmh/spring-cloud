package studyserverone.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhutong
 * @date 2019/3/26 2:00
 */
public class JDKDynamicProxy implements InvocationHandler {
    
    private Object target;
    
    public JDKDynamicProxy(Object target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("提交事务");
        Object result = method.invoke(target, args);
        System.out.println("结束事务");
        return result;
    }
    
    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
    
}
