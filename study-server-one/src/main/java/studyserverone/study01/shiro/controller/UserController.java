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
@RequestMapping("/user")
public class UserController {
    
    private final ResultMap resultMap;
    
    @Autowired
    UserController(ResultMap resultMap){
        this.resultMap = resultMap;
    }
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }
}
