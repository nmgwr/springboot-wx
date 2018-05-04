package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.modules.entity.SysOffice;
import com.nmgwr.admin.modules.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfficeController {

    @Autowired
    OfficeService officeService;

    @RequestMapping("officeList")
    public List<SysOffice> officeList(){
        return officeService.findAllOffice();
    }

}
