package custom.server.two.service;

import custom.server.two.service.impl.CustomServerTwoServerHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务
 *
 * Feign是自带断路器的，在D版本的Spring Cloud之后，它没有默认打开。需要在配置文件中配置打开它，在配置文件加以下代码：
 */
@FeignClient(value = "PRODUCT-SERVER",fallback = CustomServerTwoServerHystric.class)//只需要在FeignClient的SchedualServiceHi接口的注解中加上fallback的指定类就行了：
public interface CustomServerTwoServer {

    @GetMapping(value = "demo/demo")
    String customServerTwoServerDemo();
}
