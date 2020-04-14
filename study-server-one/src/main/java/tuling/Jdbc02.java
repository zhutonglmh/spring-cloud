package tuling;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhutong
 * @date 2020/4/14 19:16
 */
public class Jdbc02 {
    
    public static void main(String[] args) throws IOException {
    
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
       DeUser deUser = sqlSession.selectOne("org.mybatis.example.deUserMapper.query",564);
        System.out.println(deUser.getName());
    
        DeUserMapper deUserMapper = sqlSession.getMapper(DeUserMapper.class);
        System.out.println(deUserMapper.query("564").getName());
    }
    public static int insert(String id,String name,Integer age,Integer scoot) throws SQLException {
        
        String sql = "insert into de_user(id,name,age,scoot) value (?,?,?,?)";
        Connection connection = jdbc.open();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3,age);
        preparedStatement.setInt(4,scoot);
        Long result = preparedStatement.executeLargeUpdate();
        System.out.println(result);
        connection.close();
        Connection connection2 = jdbc.open();
        String sql1 = "select * from de_user where id = ?";
        PreparedStatement preparedStatement2 = connection2.prepareStatement(sql1);
        preparedStatement2.setString(1,"566");
        ResultSet resultSet = preparedStatement2.executeQuery();
        if(resultSet.next()){
            String name2 = resultSet.getString(2);
            System.out.println(name2);
        }
        return result.intValue();
    }
}
