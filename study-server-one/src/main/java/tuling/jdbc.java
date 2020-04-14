package tuling;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author zhutong
 * @date 2020/4/14 19:09
 */
public class jdbc {
    
    public static Connection open(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/zt_demo","root","zhutong960607");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
