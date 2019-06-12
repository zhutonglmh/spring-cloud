package studyserverone.proxy.jdkproxy;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhutong
 * @date 2019/3/25 23:53
 */
@Data
@ToString
public class User {
    
    private String id;
    
    private String name;
    
    private Integer age;
    
    public User(String id, int age, String name){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
