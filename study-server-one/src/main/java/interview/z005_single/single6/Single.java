package interview.z005_single.single6;

/**
 * @author zhutong
 * @date 2019/5/17 17:07
 */
public class Single {
    
    private volatile static Single single;
    
    private Single() {
    }
    
    public static Single getSingle() {
        
        if (null == single) {
            synchronized (Single.class) {
                if (null == single) {
                    single = new Single();
                }
            }
        }
        return single;
    }
    
}
