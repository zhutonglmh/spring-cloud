package studyserverone.study03.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import studyserverone.study01.shiro.server.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhutong
 * @date 2019/4/19 13:56
 */
@Service
@Slf4j
public class UserCacheServiceImpl implements UserCacheService {
    
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 按照商户id及缓存feild批量获取缓存信息
     *
     * @param feildList 缓存feild
     * @return map数据格式结果集
     */
    @Override
    public Map<String, User> hgetMap(Set<String> feildList) {
        return null;
    }
    
    /**
     * 按照商户id及缓存feild获取单个缓存信息
     *
     * @param feild 缓存feild
     * @return 缓存结果
     */
    @Override
    public User hgetCache(String feild) {
        return null;
    }
    
    /**
     * 注意: pipelinedGet接口 不保证入参、结果对应顺序。即 结果无序。
     * 按照商户id和缓存feild集合批量获取缓存信息
     *
     * @param list 缓存feild集合
     * @return list数据格式结果结
     */
    @Override
    public List<User> pipelinedGetCache(Set<String> list) {
        try {
            String key = getKey();
            return redisTemplate.executePipelined(new RedisCallback<User>() {
                @Override
                public User doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisConnection con = (StringRedisConnection) connection;
                    for (String obj : list) {
                        con.hGet(key, obj);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            log.error("CacheDaoException-物资缓存-pipelinedGetCache ERROR, errMsg = {}, e = {}", e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
    
    /**
     * 按照商户id跟feild删除缓存
     *
     * @param feild 缓存feild
     * @return 删除影响条数
     */
    @Override
    public long hdelCache(String feild) {
        return 0;
    }
    
    /**
     * 按照商户id删除缓存
     *
     * @return 删除影响条数
     */
    @Override
    public void hdelCacheByTenantId() {
        
    }
    
    /**
     * 按照商户id跟feild结果集批量删除缓存
     *
     * @param list 缓存feild集
     * @return 影响条数
     */
    @Override
    public long pipelinedDelCache(Set<String> list) {
        return 0;
    }
    
    /**
     * 按照商户id获取键的组装值
     *
     * @return 组装后的建
     */
    @Override
    public String getKey() {
        return "user";
    }
    
    /**
     * 初始化实现
     */
    @Override
    public void init() {
        
    }
    
    /**
     * 参数校验
     *
     * @param params
     * @return
     */
    @Override
    public boolean checkParam(Object... params) {
        return false;
    }
}
