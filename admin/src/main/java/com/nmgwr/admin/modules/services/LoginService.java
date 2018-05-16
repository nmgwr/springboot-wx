package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.LoginDao;
import com.nmgwr.admin.modules.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public User getUser(Map<String, String> params){
        return loginDao.login(params);
    }


}