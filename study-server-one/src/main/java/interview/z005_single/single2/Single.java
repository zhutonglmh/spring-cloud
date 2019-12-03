package interview.z005_single.single2;

/**
 * @author zhutong
 * 饿汉式
 * @date 2019/5/16 20:35
 */
public class Single {
    
     private static Single single;
     
     static {
         single = new Single();
     }
     
     public static Single getSingle(){
        return single;
     }
    
    private Single(){
        
    }
}
