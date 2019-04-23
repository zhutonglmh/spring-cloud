package studyserverone.study01.shiro.server;

import lombok.Data;
import org.apache.shiro.realm.Realm;

import java.io.Serializable;

/**
 * @author 朱同 2019-04-16
 */
@Data
public class User implements Serializable {
    
    private Integer id;

    private String userName;

    private String passWord;

    private String role;

}