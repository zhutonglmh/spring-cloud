package custom.server.two;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 讲述了如何通过RestTemplate+Ribbon去消费服务，这篇文章主要讲述如何通过Feign去消费服务。
 * Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。
 * 它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。
 * Feign支持可插拔的编码器和解码器。
 * Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。
 *
 * 简而言之：
 *
 * Feign 采用的是基于接口的注解
 * Feign 整合了ribbon，具有负载均衡的能力
 * 整合了Hystrix(断路器)，具有熔断的能力
 *

 *
 * 断路器：Hystrix 仪表盘
 */
@SpringBootApplication
@EnableFeignClients  //开启Feign的功能：
@EnableHystrixDashboard //启用断路器监控  地址http://localhost:8765/hystrix

//@EnableDiscoveryClient
public class CustomServerTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomServerTwoApplication.class, args);
	}
    /*
     * 如果都没问题那么检查下springboot 版本如果是2.0则需要添加 ServletRegistrationBean
     * 因为springboot的默认路径不是 "/hystrix.stream"，只要在自己的项目里配置上下面的servlet就可以了
     * */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
