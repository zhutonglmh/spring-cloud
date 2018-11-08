package customserverone.customserverone.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import customserverone.customserverone.service.CustOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 通过之前注入ioc容器的restTemplate来消费product-server服务的“/demo”接口，
 * * 在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
 * * 根据服务实例在请求的时候会用具体的url替换掉服务名，代码如下：
 */
@Service
public class CustOneServiceImpl implements CustOneService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorMesage") //加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法，熔断方法直接返回了一个字符串
    @Override
    public String custOneDemo(String name) {
        return restTemplate.getForObject("http://PRODUCT-SERVER/demo/demo",String.class);
    }
    @Override
    public String errorMesage(String name){ //参数类型要和上面一致
        return "请求服务出现问题，断路器启动";
    }
}
