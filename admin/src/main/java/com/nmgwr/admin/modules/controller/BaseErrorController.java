package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ErrorEnum;
import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义错误页面、默认404、500等都统一处理
 */
@Controller
public class BaseErrorController extends AbstractErrorController {

    public BaseErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public @ResponseBody Result wxerror() {
        return ResultUtil.error(ErrorEnum.ERROR);
    }

}
