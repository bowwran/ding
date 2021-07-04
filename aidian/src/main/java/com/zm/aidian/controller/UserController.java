package com.zm.aidian.controller;

import com.zm.aidian.dao.Users;
import com.zm.aidian.service.UsersService;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/user/toLogin")
    public String user(){
        return "qiantai/login";
    }

    @RequestMapping("/user/login")
    public String login(String name, String pwd, HttpSession session, Model model){
        Users users = usersService.selectNameByPwd(name, pwd);
        if (users!=null){
            session.setAttribute("user",users);
            return "redirect:/qiantai/allMenus";
        }else {
            model.addAttribute("msg","用户名或密码错误！");
            return "qiantai/login";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/toLogin";
    }

    @RequestMapping("/user/toRegister")
    public String toRegister(){
        return "/qiantai/register";
    }
    @RequestMapping("/user/register")
    public String register(HttpServletRequest request,Model model){
        String u_name = request.getParameter("name");
        String u_pwd = request.getParameter("pwd");
        String u_realname = request.getParameter("realname");
        String u_sex = request.getParameter("sex");
        String u_age = request.getParameter("age");
        int age = Integer.parseInt(u_age);
        String u_card = request.getParameter("card");
        String u_address = request.getParameter("address");
        String u_phone = request.getParameter("phone");
        String u_email = request.getParameter("email");
        String u_code = request.getParameter("code");
        Users users = usersService.queryByName(u_name);
        if (users!=null){
            model.addAttribute("message","该用户名已存在！");
            return "qiantai/register";
        }else {
            usersService.addUser(new Users(u_name,u_pwd,u_realname,
                    u_sex,age,u_card,u_address,u_phone,u_email,u_code));
        }
        return "qiantai/login";
    }
    @RequestMapping("/user/toCenter")
    public String toCenter(HttpSession session,Model model){
        Users user = (Users)session.getAttribute("user");
        if (user!=null) {
            Integer id = user.getId();
            Users users = usersService.queryById(id);
            model.addAttribute("users", users);
            return "qiantai/center";
        }else {
            return "qiantai/error";
        }
    }

    @RequestMapping("/user/update")
    public String update(HttpSession session,@Param("name") String name, @Param("pwd") String pwd, @Param("realname") String realname, @Param("sex") String sex, @Param("age") Integer age, @Param("card") String card, @Param("address") String address, @Param("Phone") String phone, @Param("email")String email, @Param("code")String code){
        Users s_user = (Users)session.getAttribute("user");
        Integer id = s_user.getId();
        Users user = new Users(id,name, pwd, realname, sex, age, card, address, phone, email, code);
        usersService.updateUser(user);
        System.out.println(sex);
        session.invalidate();
        return "redirect:/user/login";
    }



}
