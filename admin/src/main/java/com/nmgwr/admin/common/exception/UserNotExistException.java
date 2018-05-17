package com.nmgwr.admin.common.exception;

import org.apache.shiro.authc.DisabledAccountException;

public class UserNotExistException extends DisabledAccountException {
    public UserNotExistException(){
        super();
    }
}
