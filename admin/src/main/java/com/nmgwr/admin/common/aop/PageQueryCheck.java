package com.nmgwr.admin.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nmgwr.admin.common.config.ResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * 分页查询、检验分页参数
 */
@Configuration
@Aspect
public class PageQueryCheck {

    /**
     * 扫描所有Controller下方法名为Page结尾的方法，校验实体类里是否有pageNum和pageSize俩参数
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.nmgwr..*Controller.*Page(..))")
    public Object pageQueryParamsCheck(final ProceedingJoinPoint pjp)throws Throwable{
        try {
            Object[] args = pjp.getArgs();
            JSONObject params = JSON.parseObject(JSON.toJSONString(args[0]));
            if(StringUtils.isEmpty(params.get("pageNum")) || (Integer)params.get("pageNum") == 0){
                return ResultUtil.error("2001","参数有误，分页查询【pageNum】为空或者为0！");
            }
            if(StringUtils.isEmpty(params.get("pageSize")) || (Integer)params.get("pageSize") == 0){
                return ResultUtil.error("2001","参数有误，分页查询【pageSize】为空或者为0！");
            }
            Object o = pjp.proceed();
            return o;
        }catch (Throwable e){
            throw e;
        }
    }

}
