package com.zm.aidian.service.impl;

import com.zm.aidian.Mapper.AdminMapper;
import com.zm.aidian.dao.Admin;
import com.zm.aidian.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectNameByPwd(String name, String pwd) {
        return adminMapper.selectNameByPwd(name, pwd);
    }

    @Override
    public Integer updateAdminPwd(Admin admin) {
        return adminMapper.updateAdminPwd(admin);
    }

    @Override
    public Admin queryByName(String name) {
        return adminMapper.queryByName(name);
    }

    @Override
    public Integer addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }
}

