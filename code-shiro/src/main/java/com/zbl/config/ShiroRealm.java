package com.zbl.config;

import com.zbl.pojo.User;
import com.zbl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zbl
 * @Date: 15:52 2020/3/1
 * @Description: 自定义Realm
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //认证 登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

        //获取主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {
            return null;//抛出异常 UnknownAccountException
        }

        Session session = subject.getSession();
        session.setAttribute("loginUser", user);
        userToken.setRememberMe(true);
        //密码认证
        return new SimpleAuthenticationInfo(user , user.getPwd(), ByteSource.Util.bytes(user.getName() + user.getSalt()),  getName());
    }

    //授权 权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权");

        //拿到当前登录对象
        //Subject subject = SecurityUtils.getSubject();
        //User user = (User)subject.getPrincipal();
        User user =(User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission(user.getPerms());
        return info;
    }


}
