package com.nmgwr.admin.common.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;


@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
    // session 在redis过期时间是30分钟1800秒
    private static int expireTime = 1800;

    private static String prefix = "shiro-session:";

    @Resource
    public  RedisTemplate<String, Object> redisTemplate;

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        logger.debug("doReadSession:{}", sessionId);
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(toUTF8Str(prefix + sessionId.toString()));
        }
        return session;
    }

    private Serializable save(Session session){
        RedisSerializer redisSerializer = new StringRedisSerializer();
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
            System.out.println("-----------sout----------------redis key序列化----------------");
            logger.info(("---------------logger------------redis key序列化----------------"));
        Serializable sessionId = super.doCreate(session);
        logger.debug("doCreate:{}", session.getId());
        redisTemplate.opsForValue().set(toUTF8Str(prefix + sessionId.toString()), session);
        return sessionId;
    }

    @Override
    public Serializable create(Session session) {
        return save(session);
    }

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        return save(session);
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("doReadSession:{}", sessionId);
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(toUTF8Str(prefix + sessionId.toString()));
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        if(session != null && session.getId() != null){
            logger.debug("doUpdate:{}", session.getId());
            String key = prefix + session.getId().toString();
            redisTemplate.opsForValue().set(toUTF8Str(key), session);
            String userKey = "shiro-session-user:" + session.getId().toString();
            String subjectKey = "shiro-session-subject:" + session.getId().toString();
            redisTemplate.expire(toUTF8Str(subjectKey), expireTime, TimeUnit.SECONDS);
            redisTemplate.expire(toUTF8Str(userKey), expireTime, TimeUnit.SECONDS);
            redisTemplate.expire(toUTF8Str(key), expireTime, TimeUnit.SECONDS);
        }
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        logger.debug("doDelete:{}", session.getId());
        super.doDelete(session);
        String userKey = "shiro-session-user:" + session.getId().toString();
        String subjectKey = "shiro-session-subject:" + session.getId().toString();
        redisTemplate.delete(toUTF8Str(userKey));
        redisTemplate.delete(toUTF8Str(subjectKey));
    }

    private String toUTF8Str(String str){
        try {
            return new String(str.getBytes("ISO8859-1"),"utf-8");
//            return URLEncoder.encode(new String(str.getBytes("UTF-8")),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }



}