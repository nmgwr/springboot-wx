package com.nmgwr.admin.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nmgwr.admin.modules.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Configuration
@Aspect
public class DeleteAddParams {

    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    //session缓存类型
    @Value("${spring.session-type}")
    private String sessionType;

    /**
     * 扫描所有Controller下方法名为update的方法，在更新的时候初始化updateUser、updateDate
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.nmgwr..*Controller.delete(..))")
    public Object deleteAddParams(final ProceedingJoinPoint pjp)throws Throwable{
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            User user;
            if(sessionType != null && sessionType.equals("redis")){
                user = (User)redisTemplate.opsForValue().get("shiro-session-user:" + request.getSession().getId());
            }else {
                user = (User)request.getSession().getAttribute("userInfo");
            }
            JSONObject json = JSON.parseObject(JSONObject.toJSONString(pjp.getArgs()[0]));
            json.put("updateUser",user.getId());
            json.put("updateDate",new Date());
            json.put("delFlag","1");
            Object object = JSONObject.toJavaObject(json,pjp.getArgs()[0].getClass());
            Object[] objs = pjp.getArgs();
            objs[0] = object;
            Object o = pjp.proceed(objs);
            return o;
        }catch (Throwable e){
            throw e;
        }
    }

}
