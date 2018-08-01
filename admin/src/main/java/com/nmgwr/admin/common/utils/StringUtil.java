package com.nmgwr.admin.common.utils;

import java.util.UUID;

public class StringUtil {

    /**
     * 生成32位uuid
     * @return
     */
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

}
