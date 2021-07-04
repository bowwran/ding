package com.zm.aidian.Mapper;

import com.zm.aidian.dao.Menus;
import com.zm.aidian.dto.MenusDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenusMapper {
//查询全部菜单信息
    List<MenusDTO> selectAllMenusDTO();
//    通过id查询菜单信息
    Menus selectMenuById(@Param("id")Integer id);
//    添加菜单
    Integer addMenu(Menus menus);
//    修改菜单
    Integer updateMenu(Menus menus);
//    通过菜单id删除菜单
    Integer deleteMenuById(@Param("id")Integer id);
}
