package com.zm.aidian.service;

import com.zm.aidian.dao.Admin;


public interface AdminService {
    Admin selectNameByPwd(String name,String pwd);

    //    修改用户密码
    Integer updateAdminPwd(Admin admin);
    //    通过用户名查询用户信息
    Admin queryByName(String name);

    //    增加用户
    Integer addAdmin(Admin admin);
}
