package studyserverone.study03.redis.cache;

import studyserverone.study01.shiro.server.User;

/**
 * @author zhutong
 * @date 2019/4/19 13:27
 */
public interface UserCacheService extends ScmCacheService<String, User> {
    
}
