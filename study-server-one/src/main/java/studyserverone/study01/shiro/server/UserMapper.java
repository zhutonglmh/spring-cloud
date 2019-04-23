package studyserverone.study01.shiro.server;
   

/**
 * 用户接口
 * @author 朱同
 */
public interface UserMapper {
    
    /**
     * 删除
     * @param id 入参
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 新增
     * @param record 入参
     * @return 结果
     */
    int insert(User record);
    
    /**
     * 选择性插入
     * @param record 入参
     * @return 结果
     */
    int insertSelective(User record);
    
    /**
     * 主键查询
     * @param id 主键
     * @return 查询结果
     */
    User selectByPrimaryKey(Integer id);
    
    /**
     * 主键查询
     * @param id 主键
     * @return 查询结果
     */
    User selectByPrimaryName(String userName);
    
    /**
     * 修改
     * @param record 参数
     * @return 结果
     */
    int updateByPrimaryKeySelective(User record);
    
    /**
     * 修改
     * @param record 参数
     * @return 结果
     */
    int updateByPrimaryKey(User record);
}