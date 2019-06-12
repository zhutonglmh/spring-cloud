package com.zt.demo.design.module.single.single5;

/**
 * @author zhutong
 * @date 2019/5/17 17:05
 */
public class Single {
    
    private static Single single;
    
    private Single() {
    }
    
    
    public static Single getSingle() {
        
        if (null == single) {
            synchronized (Single.class) {
                single = new Single();
            }
        }
        return single;
    }
}
