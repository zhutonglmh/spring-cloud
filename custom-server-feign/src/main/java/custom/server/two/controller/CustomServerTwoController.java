package custom.server.two.controller;

import custom.server.two.service.CustomServerTwoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("hello")
public class CustomServerTwoController {

    @Resource
    //@Autowired 编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    private CustomServerTwoServer customServerTwoServer;

    @GetMapping("hello")
    public String CustomServerTwoDemo(){
        return "service-feign :"+customServerTwoServer.CustomServerTwoServerDemo();
    }
}
