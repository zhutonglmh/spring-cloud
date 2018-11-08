Shiro能做什么呢？

验证用户身份
用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
在非 web 或 EJB 容器的环境下可以任意使用Session API
可以响应认证、访问控制，或者 Session 生命周期中发生的事件
可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
支持单点登录(SSO)功能****
支持提供“Remember Me”服务，获取用户关联信息而无需登录

Authentication（认证）, Authorization（授权）, Session Management（会话管理）,
 Cryptography（加密）被 Shiro 框架的开发团队称之为应用安全的四大基石。那么就让我们来看看它们吧：
