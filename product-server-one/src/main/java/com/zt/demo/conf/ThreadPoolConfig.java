//package com.zt.demo.conf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * @author zhutong
// * @date 2018/12/26 15:06
// */
//@Configuration
//public class ThreadPoolConfig {
//    
//    /**
//     * 配置线程池
//     * @return
//     */
//    @Bean(name = "asyncPoolTaskExecutor")
//    public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        //todo 放到配置文件中
//        //最少数
//        taskExecutor.setCorePoolSize(20);
//        //最大数
//        taskExecutor.setMaxPoolSize(200);
//        //缓存队列
//        taskExecutor.setQueueCapacity(25);
//        //允许的空闲时间
//        taskExecutor.setKeepAliveSeconds(200);
//        //线程名称前缀
//        taskExecutor.setThreadNamePrefix("scm-stock-");
//        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
//        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        taskExecutor.initialize();
//        return taskExecutor;
//    }
//}
