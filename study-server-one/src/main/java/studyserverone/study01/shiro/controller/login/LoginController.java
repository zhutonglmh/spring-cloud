package studyserverone.study01.shiro.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import studyserverone.study01.shiro.conf.ResultMap;
import studyserverone.study01.shiro.server.User;
import studyserverone.study01.shiro.server.UserMapper;

import javax.annotation.Resource;

/**
 * @author zhutong
 * @date 2019/4/16 15:42
 */
@RestController
public class LoginController {
    
    
    private final ResultMap resultMap;
    @Resource
    private UserMapper userMapper;
    
    @Autowired
    LoginController(ResultMap resultMap){
        this.resultMap = resultMap;
    }
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public ResultMap notLogin() {
        return resultMap.success().message("您尚未登陆！");
    }
    
    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public ResultMap notRole() {
        return resultMap.success().message("您没有权限！");
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return resultMap.success().message("成功注销！");
    }
    
    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        User user = userMapper.selectByPrimaryName(username);
        if ("user".equals(user.getRole())) {
            return resultMap.success().message("欢迎登陆");
        }
        if ("admin".equals(user.getRole())) {
            return resultMap.success().message("欢迎来到管理员页面");
        }
        return resultMap.fail().message("权限错误！");
    }
}
