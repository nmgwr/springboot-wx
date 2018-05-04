package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.OfficeDao;
import com.nmgwr.admin.modules.entity.SysOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    OfficeDao dao;


    public List<SysOffice> findAllOffice(){
        return dao.all();
    }

}
