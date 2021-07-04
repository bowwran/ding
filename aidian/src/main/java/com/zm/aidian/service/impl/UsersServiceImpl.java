package com.zm.aidian.service.impl;

import com.zm.aidian.Mapper.UsersMapper;
import com.zm.aidian.dao.Users;
import com.zm.aidian.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public Users selectNameByPwd(String name, String pwd) {
        Users users = usersMapper.selectNameByPwd(name, pwd);
        return users;
    }

    @Override
    public Integer updateUser(Users users) {
        return usersMapper.updateUser(users);
    }

    @Override
    public Users queryByName(String name) {
        return usersMapper.queryByName(name);
    }

    @Override
    public Integer addUser(Users users) {
        return usersMapper.addUser(users);
    }

    @Override
    public Users queryById(Integer id) {
        return usersMapper.queryById(id);
    }

    @Override
    public int queryIdByName(String name) {
        return usersMapper.queryIdByName(name);
    }
}
