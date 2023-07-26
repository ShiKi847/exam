package com.example.exam.realm;

import com.example.exam.entity.User;
import com.example.exam.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private UserService userservice;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //将来的认证代理
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userservice.queryByAccount(token.getUsername());
        if(user==null) {return null;}
        //希望你通过账号,查询用户对象回来
        //参数1:认证成功存会话的信息
        //参数2:从数据库中获取的密码
        //参数3:this的名字
        return new SimpleAccount(user,user.getUsrPassword(), ByteSource.Util.bytes(user.getUsrSalt()),super.getName());//内部认证
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
