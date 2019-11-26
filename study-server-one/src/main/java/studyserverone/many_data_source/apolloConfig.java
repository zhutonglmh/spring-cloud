package studyserverone.many_data_source;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhutong
 * @date 2019/8/8 21:01
 */
@Configuration
@EnableApolloConfig(value = "application",order = 0)
public class apolloConfig {
}
