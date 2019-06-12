package studyserverone.single.single4;

/**
 * @author zhutong 懒汉式 不推荐用 同步方法效率低
 * @date 2019/5/17 17:01
 */
public class Single {
    
    private static Single single;
    private Single(){
        
    }
    public static synchronized Single getSingle(){
        
        if(null == single){
            single = new Single();
        }
        return single;
    }
}
