package studyserverone.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Service;

/**
 * @author zhutong
 * @date 2019/6/6 14:55
 */
@JobHandler(value = "zhutongHandle")
@Service
public class DemoJobHandle extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("调度任务开始执行了");
        XxlJobLogger.log("XXL-JOB, Hello World.");
        XxlJobLogger.log("XXL-JOB, Hello World.");
        return SUCCESS;
    }
}
