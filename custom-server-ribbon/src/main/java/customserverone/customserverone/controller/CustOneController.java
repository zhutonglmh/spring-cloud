package customserverone.customserverone.controller;

import customserverone.customserverone.service.CustOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 */
@RestController
@RequestMapping("hello")
public class CustOneController {

    @Autowired
    private CustOneService custOneService;

    //http://localhost:8764/custOneController/custOneDemo   负载均衡  两个服务为同一个app name
    @GetMapping("/hello")
    public String custOneDemo(){

        return "service-ribbon:"+custOneService.custOneDemo("");
    }
}
