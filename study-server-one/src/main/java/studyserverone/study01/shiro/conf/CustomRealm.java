package studyserverone.study01.shiro.conf;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import studyserverone.study01.shiro.server.User;
import studyserverone.study01.shiro.server.UserMapper;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhutong
 * @date 2019/4/16 14:56
 */
public class CustomRealm extends AuthorizingRealm {
    
    private UserMapper userMapper;
    
    @Resource
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        User user = userMapper.selectByPrimaryName(token.getUsername());
        if(null == user){
            throw new AccountException("用户不存在");
        }
        String password = user.getPassWord();
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
    
    /**
     * 获取授权信息
     *
     * @param principalCollection  密码认证令牌
     * @return 认证结果
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        User user = userMapper.selectByPrimaryName(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(user.getRole());
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }
}