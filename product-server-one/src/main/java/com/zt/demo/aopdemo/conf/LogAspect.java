package com.zt.demo.aopdemo.conf;

import com.zt.demo.aopdemo.annotation.MyAnnotation;
import com.zt.demo.aopdemo.service.UserValidator;
import com.zt.demo.aopdemo.service.impl.UserValidatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author zhutong
 * @date 2019/3/22 14:59
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    
//    @DeclareParents(value = "com.zt.demo.aopdemo.service.impl*", defaultImpl = UserValidatorImpl.class)
//    @Autowired
//    private UserValidator userValidator;
    
    
    
    @Pointcut("execution(public * com.zt.demo.aopdemo.service.impl.*.*(..))")
    private void logPoint(){
        
    }
    
    @Before("logPoint()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
    
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("zt-00000001******************:" + arg.toString());
        }
    }
    
    
    
    
    
    
    
    
    
    @Pointcut("@annotation(com.zt.demo.aopdemo.annotation.MyAnnotation)")
    private void logPoint2(){
        
    }
    
    @After("logPoint2()")
    public void doAfter(JoinPoint joinPoint) throws Throwable{
        
    }
    
    @AfterReturning(pointcut = "logPoint2()",returning = "returnMsg")
    public void doAfterReturn(JoinPoint joinPoint,Object returnMsg){
        System.out.println("afterReturn-------------------------------------"+returnMsg);
    }
    
    @AfterThrowing(pointcut = "logPoint2()",throwing = "exception")
    public void doAfterReturn(JoinPoint joinPoint,Throwable exception){
        System.out.println("afterThrowing-------------------------------------"+exception);
    }
   
    @Around("logPoint2()")
    public Object doAfterReturn2(ProceedingJoinPoint joinPoint){
        
        Object result = null;
        try {
            result = joinPoint.proceed();
            System.out.println("afterThrowing-------------------------------------"+result.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
    
    
}
