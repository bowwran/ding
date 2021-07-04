package com.zm.aidian.service;

import com.zm.aidian.dao.Menus;
import com.zm.aidian.dto.MenusDTO;


import java.util.List;

public interface MenusService {
    //查询全部菜单信息
    List<MenusDTO> selectAllMenusDTO();

    //    通过id查询菜单信息
    Menus selectMenuById( Integer id);

    //    添加菜单
    Integer addMenu(Menus menus);
    //    修改菜单
    Integer updateMenu(Menus menus);
    //    通过菜单id删除菜单
    Integer deleteMenuById(Integer id);
}
