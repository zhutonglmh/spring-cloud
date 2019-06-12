package studyserverone.single.single1;

/**
 * 
 * 饿汉式
 * @author zhutong
 * @date 2019/5/15 11:17
 */
public class Single {
    
    private static Single single = new Single();
    private Single(){
        
    }

    
    public static Single getSingle(){
        return single;
    }
    
    
}
