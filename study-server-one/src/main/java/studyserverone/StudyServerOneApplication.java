package studyserverone;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 朱同 启动类
 */


@ServletComponentScan
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EnableApolloConfig
public class StudyServerOneApplication {
    

    public static void main(String[] args) {
        
        SpringApplication.run(StudyServerOneApplication.class, args);
        System.out.println("启动成功");
    }
}
