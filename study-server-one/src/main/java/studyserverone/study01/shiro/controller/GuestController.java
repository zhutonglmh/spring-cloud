package studyserverone.study01.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import studyserverone.study01.shiro.conf.ResultMap;

/**
 * @author zhutong
 * @date 2019/4/16 15:24
 */
@RestController
@RequestMapping("/guest")
public class GuestController {
    
    private final ResultMap resultMap;
    
    @Autowired
    GuestController(ResultMap resultMap){
        this.resultMap = resultMap;
    }
    
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }
    
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap submitLogin() {
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    }
}
