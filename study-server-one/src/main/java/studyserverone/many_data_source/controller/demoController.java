package studyserverone.many_data_source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import studyserverone.easy_poi.entity.ScmYearEnd;
import studyserverone.many_data_source.service.DemoService;

import java.util.Date;

/**
 * @author zhutong
 * @date 2019/8/7 15:25
 */
@RequestMapping("demo")
@RestController
public class demoController {
    
    @Autowired
    private DemoService demoService;
    @RequestMapping(value = "demo2",method = RequestMethod.GET)
    public String demo2() throws Exception{
    
        ScmYearEnd scmYearEnd = new ScmYearEnd();
        scmYearEnd.setCreateTime(new Date());
        scmYearEnd.setId("8");
        scmYearEnd.setCreateUser("1");
        scmYearEnd.setDeleteFlag((short)1);
        scmYearEnd.setTenantId("1");
        scmYearEnd.setYearth("1");
        scmYearEnd.setUpdateTime(new Date());
        scmYearEnd.setUpdateUser("1");
        scmYearEnd.setUpdateUserName("1");
        demoService.add(scmYearEnd);
        return "success";
    }
}
