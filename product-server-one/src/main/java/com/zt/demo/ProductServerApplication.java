package com.zt.demo;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 朱同
 */
@SpringBootApplication
//@EnableEurekaClient  //声明这是一个服务提供者
@ServletComponentScan
@MapperScan({"com.zt.demo.dao"})
@Component("com.zt")
@EnableAsync
public class ProductServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServerApplication.class, args);
		System.out.println("启动完毕！");
	}
	
	/**
	 * 配置线程池
	 * @return
	 */
	@Bean(name = "asyncPoolTaskExecutor")
	public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		//todo 放到配置文件中
		//最少数
		taskExecutor.setCorePoolSize(20);
		//最大数
		taskExecutor.setMaxPoolSize(200);
		//缓存队列
		taskExecutor.setQueueCapacity(25);
		//允许的空闲时间
		taskExecutor.setKeepAliveSeconds(200);
		//线程名称前缀
		taskExecutor.setThreadNamePrefix("scm-stock-");
		// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();
		return taskExecutor;
	}
	
	@Bean
	public AsyncEventBus eventBus(ThreadPoolTaskExecutor asyncPoolTaskExecutor){
		return new AsyncEventBus(asyncPoolTaskExecutor);
	}
	
	
}
