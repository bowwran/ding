package com.zm.aidian.controller;

import com.zm.aidian.dao.Menus;
import com.zm.aidian.dao.Notices;
import com.zm.aidian.dao.Types;
import com.zm.aidian.dto.MenusDTO;
import com.zm.aidian.service.MenusService;
import com.zm.aidian.service.NoticesService;
import com.zm.aidian.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;


@Controller
public class MenusController {
    @Autowired
    private MenusService menusService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private NoticesService noticesService;

//    展示菜单
    @RequestMapping("/menus/list")
    public String selectAllMenus(Model model){
        Collection<MenusDTO> menusList = menusService.selectAllMenusDTO();
        model.addAttribute("menu_list",menusList);
        return "/admin/menuList";
    }

//    展示前台菜单
    @RequestMapping("/qiantai/allMenus")
    public String AllMenus(Model model){
        Collection<MenusDTO> list = menusService.selectAllMenusDTO();
        model.addAttribute("menuList",list);
        Collection<Types> typesList = typeService.selectAllType();
        model.addAttribute("typeList",typesList);
        Collection<Notices> noticesList = noticesService.selectAllNotices();
        model.addAttribute("noticesList",noticesList);
        return "/qiantai/allMenus";
    }

//    跳转到添加菜单页面
    @RequestMapping("/menus/toAddPage")
    public String toAddPage(Model model){
        //        查询所有类型，以供选择
        Collection<Types> types = typeService.selectAllType();
        model.addAttribute("types",types);
        return "admin/menu_add";
    }

//    添加菜单
    @RequestMapping("/menus/addMenu")
    public String addMenu(Menus menus){
        menusService.addMenu(menus);
        return "redirect:/menus/list";
    }

    //    跳转到修改菜单页面
    @RequestMapping("/menus/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
//        根据id查出相应信息
        Menus menus = menusService.selectMenuById(id);
//        将信息返回页面
        model.addAttribute("menu",menus);
//        查询所有类型，以供选择
        Collection<Types> types = typeService.selectAllType();
        model.addAttribute("types",types);
        return "admin/menu_update";
    }

//    修改菜单信息
    @PostMapping("/menus/update")
    public String updateMenu(Menus menus){
        menusService.updateMenu(menus);
        return "redirect:/menus/list";
    }

//    删除菜单信息
    @RequestMapping("/menus/delete/{id}")
    public String deleteMenu(@PathVariable("id")Integer id){
        menusService.deleteMenuById(id);
        return "redirect:/menus/list";
    }

}
