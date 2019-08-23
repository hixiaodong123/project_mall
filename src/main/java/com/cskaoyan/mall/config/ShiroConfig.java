package com.cskaoyan.mall.config;

import com.cskaoyan.mall.realm.CustomRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    /*@Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }*/
    //SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(CustomRealm realm, EhCacheManager cacheManager,
                                                     DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setLoginUrl("/index");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/index","anon");
        filterChainDefinitionMap.put("/admin/auth/login","anon");

        filterChainDefinitionMap.put("/wx/auth/login","anon");
        filterChainDefinitionMap.put("wx/auth/register","anon");
        filterChainDefinitionMap.put("wx/auth/regCaptcha","anon");

        filterChainDefinitionMap.put("/wx/**","anon");
        //filterChainDefinitionMap.put("/hello","perms[hello]");
//        filterChainDefinitionMap.put("/admin/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /*声明式权限注解的组件*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /*对没有通过授权的部分进行异常处理*/
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("org.apache.shiro.authz.AuthorizationException","/exception");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        return simpleMappingExceptionResolver;
    }
    /*缓存*/
    @Bean
    public EhCacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:shiro-cache.xml");
        return cacheManager;
    }
    /*session*/
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new MallSessionManager();
        sessionManager.setGlobalSessionTimeout(600000);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }
}
