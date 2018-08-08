package spring.cloud.server.product.productserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("demo")
@RestController
public class Demo {

    @GetMapping("/demo")
    public String demo(){
        return "8762: 朱同";
    }
}
