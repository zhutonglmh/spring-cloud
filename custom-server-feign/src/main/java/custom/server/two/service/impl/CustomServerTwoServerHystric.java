package custom.server.two.service.impl;

import custom.server.two.service.CustomServerTwoServer;
import org.springframework.stereotype.Component;

/*
* 断路器返回信息
* CustomServerTwoServerHystric需要实现CustomServerTwoServer 接口，并注入到Ioc容器中，代码如下
* */
@Component
public class CustomServerTwoServerHystric implements CustomServerTwoServer {

    @Override
    public String CustomServerTwoServerDemo() {
        return "哈哈哈哈哈，聪明的人类，我是断路器";
    }
}
