package studyserverone.proxy.jdkproxy;



/**
 * @author zhutong
 * @date 2019/3/26 1:48
 */
public interface IUserDao {
    
    void save(User user);
    static void ss(){
        System.out.println("123456");
    };
}
