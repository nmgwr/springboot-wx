package com.nmgwr.admin.common.config;

import com.nmgwr.admin.modules.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一处理异常
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        log.error(e.getMessage());
        if (e.getClass().getName().equals("org.apache.shiro.authz.UnauthorizedException")){
            return ResultUtil.error(ErrorEnum.NO_AUTH);
        }else {
            e.printStackTrace();
            return ResultUtil.error(ErrorEnum.UNKONW_ERROR);
        }
    }

}
