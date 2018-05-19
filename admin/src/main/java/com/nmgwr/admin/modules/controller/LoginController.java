package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ErrorEnum;
import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.common.exception.UserNotExistException;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController extends BaseController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    //session缓存类型
    @Value("spring.session-type")
    private String sessionType;

    /**
     * 登陆
     * @param params
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestParam Map<String,String> params, HttpServletRequest req){
        Result result = new Result();
        UsernamePasswordToken token = new UsernamePasswordToken(params.get("loginName"),params.get("passwd"));
        Subject subject = SecurityUtils.getSubject();
        Map data = new HashMap<String,Object>();
        try{
            subject.login(token);
            if(sessionType != null && sessionType.equals("redis")){
                data.put("userInfo",(User)redisTemplate.opsForValue().get("shiro-session-user:" + subject.getSession().getId().toString()));
            }else {
                data.put("userInfo",(User)subject.getSession().getAttribute("userInfo"));
            }
            data.put("token",subject.getSession().getId());
            result = ResultUtil.success(data);
        }catch (LockedAccountException e){
            result = ResultUtil.error(ErrorEnum.USER_STATUS_ERROR);
        }catch (UserNotExistException e){
            result = ResultUtil.error(ErrorEnum.USER_NOT_FIND);
        }catch (IncorrectCredentialsException e){
            result = ResultUtil.error(ErrorEnum.PASSWD_ERROR);
        }finally {
            return result;
        }
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultUtil.success();
    }



}
