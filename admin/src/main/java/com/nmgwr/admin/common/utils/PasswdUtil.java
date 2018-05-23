package com.nmgwr.admin.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswdUtil {

    public static String getPasswdByLoginName(String loginName,String passwd){
        return new Md5Hash(passwd,loginName + "123!@#---$%6",2).toString();
    }

    public static void main(String[] args) {
        System.out.println(getPasswdByLoginName("nmgwr","123456"));
    }

}
