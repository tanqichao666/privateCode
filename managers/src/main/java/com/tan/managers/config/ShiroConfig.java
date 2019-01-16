package com.tan.managers.config;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    /**
     * 自定义的Realm
     */
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }
//    @Bean
//    public DefaultWebSecurityManager  securityManager(){
//    	DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager(this.myShiroRealm());
//        return securityManager;
//    }
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
      //过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("shiro", new ShiroAuthenticatingFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/api/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "shiro");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    
    @Bean("securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("sessionManager")SessionManager sessionManager) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(this.myShiroRealm());
		manager.setSessionManager(sessionManager);
		return manager;
	}
    
    @Bean("sessionManager")
	public SessionManager sessionManager(){
		CustomSessionManager manager = new CustomSessionManager();
        manager.setSessionDAO(new EnterpriseCacheSessionDAO());
		return manager;
	}
}
