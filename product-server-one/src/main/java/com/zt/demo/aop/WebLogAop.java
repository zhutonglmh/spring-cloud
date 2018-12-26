package com.zt.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 朱同
 */
@Aspect
@Component
public class  WebLogAop {
    
//    @Pointcut("execution(public * com.zt.demo.controller..*.*(..))")
//    public void pointCut(){
//        
//    }
//    
//    @Before(value = "pointCut()")
//    public void doBefore(JoinPoint joinPoint){
//    
//        System.out.println("before--------------------------------------"+joinPoint);
//    }
//    
//    @After(value = "pointCut()")
//    public void doAfter(JoinPoint joinPoint){
//        System.out.println("after---------------------------------------"+joinPoint);
//    }
//    
//    @AfterReturning(pointcut = "pointCut()",returning = "returnMsg")
//    public void doAfterReturn(JoinPoint joinPoint,Object returnMsg){
//        System.out.println("afterReturn-------------------------------------"+returnMsg);
//    }
//    
//    @AfterThrowing(pointcut = "pointCut()",throwing = "exception")
//    public void doAfterReturn(JoinPoint joinPoint,Throwable exception){
//        System.out.println("afterThrowing-------------------------------------"+exception);
//    }
}
