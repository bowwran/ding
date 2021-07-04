package com.zm.aidian.Mapper;


import com.zm.aidian.dao.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UsersMapper {
    Users selectNameByPwd(String name, String pwd);

    //    修改用户密码
    Integer updateUser(Users users);

    //    通过用户名查询用户信息
    Users queryByName(String name);
    //    增加用户
    Integer addUser(Users users);

    //通过id查询用户信息
    Users queryById(Integer id);
    //通过名字查询id
    int queryIdByName(String name);
}
