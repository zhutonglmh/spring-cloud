package studyserverone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


/**
 * @author 朱同 启动类
 */
@SpringBootApplication
@MapperScan({"studyserverone.study01.shiro.server","studyserverone.easyPOI.dao"})
@Component("studyserverone")
public class StudyServerOneApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StudyServerOneApplication.class, args);
        System.out.println("启动成功！");
    }
}
