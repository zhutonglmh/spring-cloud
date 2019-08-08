package studyserverone.proxy.proxy;


import studyserverone.proxy.jdkproxy.User;

/**
 * @author zhutong
 * @date 2019/3/26 1:48
 */
public interface IUserDao {
    
    void save(User user);
}
