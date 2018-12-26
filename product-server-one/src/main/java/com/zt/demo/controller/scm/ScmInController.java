package com.zt.demo.controller.scm;

import com.zt.demo.entity.ScmIn;
import com.zt.demo.service.ScmInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author zhutong
 * @date 2018/12/21 16:51
 */
@RequestMapping("scm/in")
@RestController
public class ScmInController {
    
    @Autowired
    private ScmInService scmInService;
    
    @PostMapping(value = "query")
    public ScmIn queryScmInById(@RequestBody ScmIn scmIn){
        return scmInService.queryById(scmIn);
    }
}
