package studyserverone.single.single3;

/**
 * @author zhutong 懒汉式  不可用
 * @date 2019/5/16 20:42
 */
public class Single {
    
    private static Single single;
    
    public static Single getSingle(){
        
        if(null == single){
            single = new Single();
        }
        return single;
    }
    private Single(){
        
    }
}
