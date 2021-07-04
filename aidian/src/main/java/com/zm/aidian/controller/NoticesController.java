package com.zm.aidian.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zm.aidian.dao.Notices;
import com.zm.aidian.service.NoticesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class NoticesController {

    @Autowired
    private NoticesService noticesService;
    //    跳转到公告列表页面
    @RequestMapping("/notices/list")
    public String noticesList(Model model){
        Collection<Notices> noticesList = noticesService.selectAllNotices();
        model.addAttribute("notice_list",noticesList);
        return "admin/noticeList";
    }

//    跳转添加公告
    @RequestMapping("/notices/toAddPage")
    public String toAddNotice(){
        return "admin/notice_add";
    }

//    添加公告
    @RequestMapping("/notices/addNotice")
    public String addNotice(@RequestParam("name")String name, @RequestParam("content")String content){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
        String times = dateFormat.format(new Date());
        noticesService.addNotice(new Notices(name,content,times));
        return "redirect:/notices/list";
    }

//跳转到修改公告
    @RequestMapping("/notices/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable("id")Integer id,Model model){
        Notices notices = noticesService.selectNoticeById(id);
        model.addAttribute("notices",notices);
        return "admin/notice_update";
    }

//    修改公告
    @RequestMapping("/notices/updateNotice")
    public String updateNotice(@RequestParam("id")Integer id,@RequestParam("name")String name, @RequestParam("content")String content){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
        String times = dateFormat.format(new Date());
        noticesService.updateNotice(new Notices(id,name,content,times));
        return "redirect:/notices/list";
    }
//    删除公告
    @RequestMapping("/notices/deleteNotice/{id}")
    public String deleteNotice(@PathVariable("id")Integer id){
        noticesService.deleteNoticeById(id);
        return "redirect:/notices/list";
    }

    //    查询所有公共并传送到前台
    @RequestMapping("/qiantai/queryAllNotice")
    public String queryAllNoticetoQT(Model model){
        List<Notices> noticesList = noticesService.selectAllNotices();
        model.addAttribute("noticesList",noticesList);
        return "/qiantai/notice";
    }
    //    根据id查询公共并传送到前台
    @RequestMapping("/qiantai/queryNoticeById/{id}")
    public String queryNoticeByIdToQT(@PathVariable("id") Integer id, Model model){
        Notices notices = noticesService.selectNoticeById(id);
        model.addAttribute("notice",notices);
        return "/qiantai/notice";
    }

}
