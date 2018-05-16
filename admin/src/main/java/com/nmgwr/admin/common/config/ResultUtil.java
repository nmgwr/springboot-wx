package com.nmgwr.admin.common.config;

import com.nmgwr.admin.modules.entity.Result;

public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     * @param object
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode("0000");
        result.setDesc("success");
        result.setData(object);
        return result;
    }
    /**
     * 提供给部分不需要出參的接口
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 自定义错误信息
     * @param code
     * @param desc
     * @return
     */
    public static Result error(String code,String desc){
        Result result = new Result();
        result.setCode(code);
        result.setDesc(desc);
        result.setData(null);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     * @param errorEnum
     * @return
     */
    public static Result error(ErrorEnum errorEnum){
        Result result = new Result();
        result.setDesc(errorEnum.getDesc());
        result.setCode(errorEnum.getCode());
        result.setData(null);
        return result;
    }


}
