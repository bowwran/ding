package com.zm.aidian.service.impl;

import com.zm.aidian.Mapper.MenusMapper;
import com.zm.aidian.dao.Menus;
import com.zm.aidian.dto.MenusDTO;
import com.zm.aidian.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusMapper menusMapper;
    @Override
    public List<MenusDTO> selectAllMenusDTO() {
        List<MenusDTO> menusDTOList = menusMapper.selectAllMenusDTO();
        return menusDTOList;
    }

    @Override
    public Menus selectMenuById(Integer id) {
        Menus menus = menusMapper.selectMenuById(id);
        return menus;
    }

    @Override
    public Integer addMenu(Menus menus) {
        return menusMapper.addMenu(menus);
    }

    @Override
    public Integer updateMenu(Menus menus) {
        return menusMapper.updateMenu(menus);
    }

    @Override
    public Integer deleteMenuById(Integer id) {
        return menusMapper.deleteMenuById(id);
    }
}
