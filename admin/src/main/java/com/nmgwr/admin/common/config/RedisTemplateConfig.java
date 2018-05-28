package com.nmgwr.admin.common.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisTemplate配置类
 */
@Configuration
public class RedisTemplateConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置redis序列化key value防止乱码
     * @return
     */
    @Bean
    public RedisTemplate redisTemplateInit() {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //设置序列化Value的实例化对象GenericJackson2JsonRedisSerializer
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

}
