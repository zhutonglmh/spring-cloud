package studyserverone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 朱同 启动类
 */


@ServletComponentScan
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class StudyServerOneApplication {
    

    public static void main(String[] args) {
        
        SpringApplication.run(StudyServerOneApplication.class, args);
        System.out.println("启动成功！");
    }
}
