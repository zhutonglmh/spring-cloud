package com.zt.demo.interview;

/**
 * @author zhutong
 * @date 2019/5/20 20:27
 */
public class DeadLocker {
    class A{
        
    }
    class B{
        
    }
    public void DeadLocker(){
        synchronized (this){
            
        }
    }
    
    private void method(){
        
    }
}
