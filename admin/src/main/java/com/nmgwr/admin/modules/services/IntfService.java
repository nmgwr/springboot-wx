package com.nmgwr.admin.modules.services;


import com.nmgwr.admin.modules.entity.RespEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IntfService {

    /**
     * 校验接口参数
     * @param params
     * @return
     */
    public RespEntity chechData(Map<String, String> params) {
        return new RespEntity("0000");
    }

}
