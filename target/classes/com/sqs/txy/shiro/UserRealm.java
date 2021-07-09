package com.sqs.txy.shiro;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-03-31 23:03
 **/

public class UserRealm extends AuthorizingRealm {



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*System.out.println("授权逻辑！！！！！！！！！！");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //数据库的做法
        //获取当前的登录用户的角色
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User byId = userService.findById(user.getUserid());
        info.addStringPermission(byId.getUserperms());
        return info;*/
        return null;
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    /*    System.out.println("认证中。。。。。。。。。。。。。。。。");
        //判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByName(token.getUsername());
        if (user == null){
            return null;
        }
        //认证时将用户的数据带到授权时候使用
        return new SimpleAuthenticationInfo(user,user.getUserpassword(),"");*/
        return null;
    }

}
