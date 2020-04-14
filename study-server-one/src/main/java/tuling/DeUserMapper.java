package tuling;

import org.apache.ibatis.annotations.Select;

/**
 * @author zhutong
 * @date 2020/4/14 20:52
 */
public interface DeUserMapper {
    
    @Select("select * from de_user where id = #{id}")
    DeUser query(String id);
}
