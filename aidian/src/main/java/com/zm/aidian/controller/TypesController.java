package com.zm.aidian.controller;

import com.zm.aidian.Mapper.TypeMapper;
import com.zm.aidian.dao.Types;
import com.zm.aidian.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class TypesController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/types/list")
    public String typesList(Model model){
        Collection<Types> typesList = typeService.selectAllType();
        model.addAttribute("type_list",typesList);
        return "admin/menuTypeList";
    }

    @RequestMapping("/types/toAddPage")
    public String toAddPage(){
        return "admin/menuType_add";
    }

    @RequestMapping("/types/add")
    public String addType(Types types){
        typeService.addType(types);
        return "redirect:/types/list";
    }
    @RequestMapping("/types/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable("id")Integer id,Model model){
        Types type = typeService.selectTypeById(id);
        model.addAttribute("type",type);
        return "admin/menuType_update";
    }
    @RequestMapping("/types/update")
    public String updateType(Types types){
        typeService.updateType(types);
        return "redirect:/types/list";
    }
    @RequestMapping("/types/delete/{id}")
    public String deleteType(@PathVariable("id")Integer id){
        typeService.deleteTypeById(id);
        return "redirect:/types/list";
    }
}
