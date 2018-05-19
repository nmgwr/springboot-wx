package com.nmgwr.admin.common.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //注入redisTemplate、在设置residsSessionDao时用到
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * shiro过滤器、只配置了登陆不校验其他都校验、登陆登出调用shiro都在controller里触发了
     * @param securityManager
     * @return
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc",new FormAuthenticationFilter());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("shiro初始化过滤器完成");
        return shiroFilterFactoryBean;
    }

    /**
     * shiro-spring-boot-stater要求必须有该bean
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        return chainDefinition;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义realm、里边配置了登陆验证和权限验证
     * @return
     */
    @Bean
    public Realm realm() {
        WxShiroRealm myShiroRealm = new WxShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    /**
     * 自定义了session从请求头中获取
     * @param env 读取shpringboot配置文件
     * @return
     */
    @Bean
    public SessionManager sessionManager(Environment env) {
        WxSessionManager mySessionManager = new WxSessionManager();
        //如果sessionType是redis则设置resdisSessionDao、否则走默认存内存里
        String sessionType = env.getProperty("spring.session-type");
        if(sessionType != null && sessionType.equals("redis")){
            RedisSessionDao sessionDAO=new RedisSessionDao();
            sessionDAO.redisTemplate=redisTemplate;
            mySessionManager.setSessionDAO(sessionDAO);
        }
        return mySessionManager;
    }

    /**
     * 自定义shiro缓存
     * @param env 读取shpringboot配置文件
     * @return
     */
//    @Bean
//    public CacheManager cacheManager(Environment env){
//        System.out.println("明天实现");
//        return null;
//    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }




}
