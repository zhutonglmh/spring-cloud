package customserverone.customserverone;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 在微服务架构中，业务都会被拆分成一个独立的服务，服务与服务的通讯是基于http restful的。
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign。
 * 在这一篇文章首先讲解下基于ribbon+rest。
 *
 * ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。
 * ribbon 已经默认实现了这些配置bean：
 * IClientConfig ribbonClientConfig: DefaultClientConfigImpl
 * IRule ribbonRule: ZoneAvoidanceRule
 *  IPing ribbonPing: NoOpPing
 * ServerList ribbonServerList: ConfigurationBasedServerList
 * ServerListFilter ribbonServerListFilter: ZonePreferenceServerListFilter
 * ILoadBalancer ribbonLoadBalancer: ZoneAwareLoadBalancer

 * 断路器Hystrix
 * 在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），
 * 在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，单个服务通常会集群部署。
 * 由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，
 * 此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，
 * 会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应。
 * 为了解决这个问题，业界提出了断路器模型。
 * 较底层的服务如果出现故障，会导致连锁故障。当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。
 * 断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。
 * 在ribbon使用断路器
 * 改造serice-ribbon 工程的代码，首先在pox.xml文件中加入spring-cloud-starter-netflix-hystrix的起步依赖：
 */

/*
*  @EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现；
*  @EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix //启用断路器Hystrix
@EnableHystrixDashboard //启用断路器监控  地址http://localhost:8764/hystrix
public class CustomServerOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomServerOneApplication.class, args);
	}

	/*
	* 在工程的启动类中,通过@EnableDiscoveryClient向服务中心注册；并且向程序的ioc注入一个bean: restTemplate;并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。
	*/
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*
	* 如果都没问题那么检查下springboot 版本如果是2.0则需要添加 ServletRegistrationBean
	* 因为springboot的默认路径不是 "/hystrix.stream"，只要在自己的项目里配置上下面的servlet就可以了
	* */
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}
}
