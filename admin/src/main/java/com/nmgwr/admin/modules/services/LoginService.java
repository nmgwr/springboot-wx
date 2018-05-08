package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.LoginDao;
import com.nmgwr.admin.modules.entity.RespEntity;
import com.nmgwr.admin.modules.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public RespEntity Login(Map<String, String> params, HttpServletRequest req){
        RespEntity resp = new RespEntity();
        params.put("loginName",params.get("loginName"));
        //密码转MD5加密
        params.put("passwd",DigestUtils.md5DigestAsHex(params.get("passwd").getBytes()));
        User user = loginDao.login(params);
        if(user == null){
            resp.setCode("9999");
            resp.setDesc("用户不存在或密码错误");
        }else {
            if(!user.getUserStatus().equals("0")){
                resp.setCode("0001");
                resp.setDesc("用户被锁定");
            }else {
                resp.setCode("0000");
                resp.setDesc("登陆成功");
                resp.setData(user);
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
            }
        }
        return resp;
    }


}
