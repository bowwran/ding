package com.zm.aidian.controller;

import com.zm.aidian.dao.Admin;
import com.zm.aidian.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;



    @RequestMapping("/admin/login")
    public String login(String name,String pwd,
                        HttpSession session, Model model){
        Admin admin = adminService.selectNameByPwd(name, pwd);
        if (admin!=null){
            session.setAttribute("admin",admin);
            return "redirect:/menus/list";
        }else {
            model.addAttribute("msg","用户名或密码错误！");
            return "admin/login";
        }
    }
    @RequestMapping("/admin/toLogin")
    public String toLogin(){
        return "admin/login";
    }
    @RequestMapping("/admin/toRegister")
    public String toRegister(){
        return "admin/registration";
    }
    @RequestMapping("/admin/register")
    public String register(HttpServletRequest request,Model model){
        String r_name = request.getParameter("name");
        String r_pwd = request.getParameter("pwd");
        Admin admin = adminService.queryByName(r_name);
        if (admin!=null){
            model.addAttribute("message","该用户名已存在");
            return "admin/registration";
        }else {
            adminService.addAdmin(new Admin(r_name,r_pwd));
        }
        return "admin/login";
    }


    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }


    @RequestMapping("/admin/toUpdatePage")
    public String toUpdatePage(){
        return "admin/admin_update";
    }

    @RequestMapping("/admin/update")
    public String update(Admin admin,HttpSession session){
        adminService.updateAdminPwd(admin);
        session.invalidate();
        return "redirect:/admin/login";
    }
}
