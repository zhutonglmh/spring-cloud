package studyserverone.single.single7;

/**
 * @author zhutong
 * @date 2019/5/17 17:13
 */
public class Single {
    private Single(){}
    
    private static class Single2{
        private static Single single = new Single();
    }
    public static Single getSingle(){
        return Single2.single;
    }
}
