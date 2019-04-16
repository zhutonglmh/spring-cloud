package com.zt.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 朱同
 */
@Aspect
@Component
public class  WebLogAop {
    
    /**
     * 包括访问修饰符（public/private/protected）、返回类型，包名、类名、方法名、参数，其中返回类型，包名，类名，方法，参数是必须的
     */
    @Pointcut(value = "execution(public String com.zt.demo.aopdemo.service.impl.UserServiceImpl.getUser(String))")
    public void pointCut1(){}
    
    /**
     * 方法可能会有不同的方法名，不同的返回值，不同的参数列表，为了达到这种效果，我们可以使用通配符
     */
    @Pointcut("execution(public * com.zt.demo.controller..*.*(..))")
    public void pointCut2(){}
    
    
    
    /**
     * 使用within切点批示符可以达到上面例子一样的效果，within用来限定连接点属于某个确定类型的类
     */
    @Pointcut("within(com.zt.demo.aopdemo.service.impl.UserServiceImpl)")
    public void pointCut3(){}
    /**
     * 使用within切点批示符可以达到上面例子一样的效果，within用来限定连接点属于某个确定类型的类
     */
    @Pointcut("within(com.zt.demo.aopdemo.service.impl.*)")
    public void pointCut4(){}
    
    
    /**
     * 用来匹配指定方法参数
     */
    @Pointcut("args(String)")
    public void pointCut5(){}
    
    /**
     * 用来匹配指定方法参数 (多个参数)
     */
    @Pointcut("args(String,..)")
    public void pointCut6(){}
    
    /**
     * 如果当前对象引用实现了某个接口时，Spring aop使用JDK的动态代理机制来实现切面编程
     */
    @Pointcut("target(com.zt.demo.aopdemo.service.impl.UserServiceImpl)")
    public void pointCut7(){}
    
    /**
     * 如果当前对象引用的类型没有实现自接口时，spring aop使用生成一个基于CGLIB的代理类实现切面编程
     */
    @Pointcut("this(com.zt.demo.aopdemo.service.impl.UserServiceImpl)")
    public void pointCut8(){}
    
    /**
     * (这个连接点所属的目标对象的类有一个指定的注解)
     */
    @Pointcut("@target(com.zt.demo.aopdemo.annotation.MyAnnotation)")
    public void pointCut9(){}
    
    /**
     * -@args指出连接点在运行时传过来的参数的类必须要有指定的注解，参数上有指定的注解
     */
    @Pointcut("@args(com.zt.demo.aopdemo.annotation.MyAnnotation)")
    public void pointCut10(){}
    
    /**
     * -@within(指定匹配必须包括某个注解的的类里的所有连接点)
     */
    @Pointcut("@within(com.zt.demo.aopdemo.annotation.MyAnnotation)")
    public void pointCut11(){}
}
