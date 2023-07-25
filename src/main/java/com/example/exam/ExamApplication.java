package com.example.exam;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.exam.realm.MyRealm;
import com.example.exam.util.EncryptUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.example.exam.dao")
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(EncryptUtil.hashAlgorithmName);
        matcher.setHashIterations(EncryptUtil.hashIterations);
        return matcher;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(HashedCredentialsMatcher matcher , MyRealm myRealm ,
                                                               DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        myRealm.setCredentialsMatcher(matcher);
        securityManager.setRealm(myRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String,String> map=new LinkedHashMap<>();
        //路由器的过滤法则,anon放行 authc认证
        map.put("/","anon");
        map.put("/index","anon");
        map.put("/login","anon");
        map.put("/register","anon");
        map.put("/logo.png","anon");
        map.put("/logout","logout");
        map.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(map);
        factoryBean.setLoginUrl("/login");
        return factoryBean;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        ShiroDialect shiroDialect = new ShiroDialect();
        return  shiroDialect;
    }
}
