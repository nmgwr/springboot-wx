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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 未登录、返回未登陆状态信息、由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    public Result unauth(){
        return ResultUtil.error(ErrorEnum.NO_LOGIN);
    }

    @RequestMapping("/login")
    public Result login(@RequestParam Map<String,String> params){
        Result result = new Result();
        UsernamePasswordToken token = new UsernamePasswordToken(params.get("loginName"),params.get("passwd"));
        Subject subject = SecurityUtils.getSubject();
        Map data = new HashMap<String,Object>();
        try{
            subject.login(token);
            data.put("userInfo",(User)subject.getSession().getAttribute("userInfo"));
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

    @RequestMapping("/looksession")
    public Result looktest(@RequestParam Map<String,String> params, HttpServletRequest req){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        if(user != null){
            return ResultUtil.success("用户【" + user.getName() + "】已登陆:" + session.getId());
        }else {
            return ResultUtil.success("用户未登陆:" + session.getId());
        }
    }


}
