package com.sqs.txy.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-03-31 23:02
 **/

@Configuration
public class shiroConfig {
    /*
     * 创建ShiroFilterFactoryBean
     * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        /*设置Shiro内置过滤器，可以实现权限相关的拦截器
         *     常用的过滤器：
         *           anon：无需认证（授权）可以访问
         *           authc:必须认证才能访问
         *           user:如果使用rememberme的功能可以直接访问
         *           perms:该资源必须得到权限才能访问
         *           role:该资源必须得到角色权限才能访问
         * */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/**","anno");
        filterMap.put("/static/**","anno");
       /* filterMap.put("/user/login","anon");
        filterMap.put("/user/toRegister","anon");
        filterMap.put("/user/register","anon");
        //授权过滤器
        filterMap.put("/user/add","perms[admin]");
        filterMap.put("/user/update","perms[user,admin]");
        //拦截全部请求
        filterMap.put("/user/*","authc");//路径，权限
        //放行login请求
        filterMap.put("/login.html","anon");
        filterMap.put("/index.html","anon");
        //设置登录的页面请求
        factoryBean.setLoginUrl("/user/toLogin");
        //设置未授权的页面请求
        factoryBean.setUnauthorizedUrl("/user/noAut");
        factoryBean.setFilterChainDefinitionMap(filterMap);*/
        return factoryBean;
    }

    /*
     * 创建DefaultWebSecurityManager
     * */
    @Bean(name ="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //DefaultWebSecurityManager关联一个realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    /*
     *创建Realm对象
     * */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}
