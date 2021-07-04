package com.zm.aidian.controller;


import com.zm.aidian.dao.Menus;
import com.zm.aidian.dao.Orders;
import com.zm.aidian.dao.ShoppingCart;
import com.zm.aidian.dao.Users;
import com.zm.aidian.dto.OrdersDTO;
import com.zm.aidian.service.MenusService;
import com.zm.aidian.service.OrdersService;
import com.zm.aidian.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private MenusService menusService;
    @Autowired
    private UsersService usersService;
    @RequestMapping("/orders/list")
    public String ordersList(Model model){
        Collection<OrdersDTO> ordersDTOList = ordersService.selectAllOrdersDAO();
        model.addAttribute("order_list",ordersDTOList);
        return "admin/orderList";
    }

//确认订单送达或取消订单
    @RequestMapping("/orders/confirm")
    public String OrdersConfirm(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        String reqtype=request.getParameter("reqtype");
        if(reqtype.equals("delivery")){
            ordersService.updateDeliveryById(id);
        }else if(reqtype.equals("del")){
            ordersService.deleteById(id);
        }else{
            System.out.println("错误");
        }
        return "redirect:/orders/list";
    }
//    跳转到查询页面
    @RequestMapping("/orders/toSelectPage")
    public String toSelectPage(){
        return "admin/orderList_select";
    }
//    查询订单
    @RequestMapping(value = "/orders/order_select",method = RequestMethod.POST )
    public String OrderSelect(Model model,HttpServletRequest request) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        Collection<OrdersDTO> list=null;
        Integer id=null;
        String name=null;
        String time1=null;
        String time2=null;
        String a1=null;
        if (request.getParameter("userID")!=null&&(!request.getParameter("userID").equals(""))){
            id = Integer.parseInt(request.getParameter("userID"));
            list = ordersService.selectOrdersByUserId(id);
        }else if (request.getParameter("menusName")!=null&&(!request.getParameter("menusName").equals(""))){
            name=request.getParameter("menusName");
            list=ordersService.selectOrdersByMenusName(name);
        }else if (request.getParameter("saleDate")!=null&&(!request.getParameter("saleDate").equals(""))){
            time1=request.getParameter("saleDate");
            Integer a = Integer.parseInt(time1.substring(8, 10),10);// substring() 方法用于提取字符串中介于两个指定下标之间的字符。,parseInt() 方法用于将字符串参数作为有符号的十进制整数进行解析
            a1=""+(a+1);
            StringBuilder day = new StringBuilder((time1)); //StringBuilder类也代表可变字符串对象。
            day.replace(8,10,a1);
            time2 = day.toString();
            list=ordersService.selectOrderByDate(time1,time2);
        }
        model.addAttribute("lists",list);
        System.out.println(id);
        System.out.println(name);
        System.out.println(time1);
        System.out.println(time2);
        return "admin/orderList_select";
    }

//   前台 放入购物车
    @RequestMapping("/qiantai/order_addshoppingcar")
    public String addshoppingcar(HttpServletRequest request){
        HttpSession session = request.getSession();
        String menuId = request.getParameter("menuId");
        Integer id = Integer.parseInt(menuId);
        Integer sum = (Integer)session.getAttribute("sum");
        float sum1=0.0f; //总计价格
        int sum2=0; //总计数量

        if (sum==null){
            sum=1;
            session.setAttribute("sum",sum);
        }
        Menus menus = menusService.selectMenuById(id);
        String name = menus.getName();
        float price = menus.getPrice1();
        ShoppingCart shoppingCart1;

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        if (session.getAttribute("shoppingcar")!=null){
            shoppingCarts = (List<ShoppingCart>) session.getAttribute("shoppingcar");
            for (ShoppingCart shoppingCart:shoppingCarts) {
                if (name.equals(shoppingCart.getName())) {
                    sum = shoppingCart.getSums() + 1;
                    shoppingCarts.remove(shoppingCart);
                    break;
                }
            }
            shoppingCart1=new ShoppingCart(id,name,price,sum);
            shoppingCarts.add(shoppingCart1);
            session.setAttribute("shoppingcar",shoppingCarts);

            List<ShoppingCart> shoppingcar =(List<ShoppingCart>)session.getAttribute("shoppingcar");
            if (shoppingcar!=null){
            for (int i=0;i<shoppingcar.size();i++) {
                ShoppingCart shopping = shoppingcar.get(i);
                sum1=sum1+shopping.getPrice()*shopping.getSums();
                sum2=sum2+shopping.getSums();
                session.setAttribute("i",i);
            }

            session.setAttribute("sum1",sum1);
            session.setAttribute("sum2",sum2);
            }
        }else {
            shoppingCart1=new ShoppingCart(id,name,price,sum);
            shoppingCarts.add(shoppingCart1);
            session.setAttribute("shoppingcar",shoppingCarts);

            List<ShoppingCart> shoppingcar =(List<ShoppingCart>)session.getAttribute("shoppingcar");
            if (shoppingcar!=null) {
                for (int i=0;i<shoppingcar.size();i++) {
                    ShoppingCart shopping = shoppingcar.get(i);
                    sum1 = sum1 + shopping.getPrice() * shopping.getSums();
                    sum2 = sum2 + shopping.getSums();
                    session.setAttribute("i",i);
                }
                session.setAttribute("sum1", sum1);
                session.setAttribute("sum2", sum2);
            }
        }
        return "redirect:/qiantai/allMenus";
    }

//    购物车单个取消
    @RequestMapping("/qiantai/order_del")
    public String orderDel(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<ShoppingCart> shoppingCarts=(List<ShoppingCart>)session.getAttribute("shoppingcar");
        Integer id = Integer.parseInt(request.getParameter("del"));
        Integer i=0;
        for (ShoppingCart shoppingCart:shoppingCarts){
            if (i.equals(id)){
                shoppingCarts.remove(shoppingCart);
                break;
            }
            i++;
        }

        return "redirect:/qiantai/allMenus";
    }

//   前台 跳转到购物车
    @RequestMapping("/qiantai/toShoppingcartPage")
    public String toShoppingCartPage(){
        return "qiantai/shoppingcar";
    }

//    前台 提交订单
    @RequestMapping("/qiantai/submitOrder")
    public String submitOrder(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<ShoppingCart> shoppingCarts=(List<ShoppingCart>)session.getAttribute("shoppingcar");
        if (session.getAttribute("user")!=null){
            Users user = (Users) session.getAttribute("user");
            Integer userid = usersService.queryIdByName(user.getName());
            Integer delivery=0;
            String remove = request.getParameter("remove");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String day = formatter.format(date);

            if (shoppingCarts!=null){
                for (ShoppingCart shoppingCart:shoppingCarts){
                    Integer sums = shoppingCart.getSums();
                    Integer menuid = shoppingCart.getId();
                    Orders orders = new Orders(userid, menuid, sums, day, delivery);
                    ordersService.addOrder(orders);
                }
                session.removeAttribute("shoppingcar");
                session.removeAttribute("sum1");
                session.removeAttribute("sum2");
            }
            if (remove!=null){
                if (remove.equals("1")){
                    session.removeAttribute("shoppingcar");
                    session.removeAttribute("sum1");
                    session.removeAttribute("sum2");
                }
            }
        }else {
            return "redirect:/user/login";
        }
        return "redirect:/qiantai/allMenus";
    }

    //前台订单查询
    //    跳转到查询页面
    @RequestMapping("/qiantai/toSelectPage")
    public String toQiantaiSelectPage(){
        return "qiantai/order";
    }
    //    查询菜单
    @RequestMapping(value = "/qiantai/selectUserOrder",method = RequestMethod.POST )
    public String qiantaiOrderSelect(Model model,HttpServletRequest request) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Collection<OrdersDTO> list=null;

        String name=null;
        String time1=null;
        String time2=null;
        String a1;
        if (session.getAttribute("user")!=null){
            Users user = (Users) session.getAttribute("user");
            Integer id = user.getId();//用户
            if (request.getParameter("menusName")!=null&&(!request.getParameter("menusName").equals(""))){
                name=request.getParameter("menusName");
                list=ordersService.selectOrdersByMenusNameAndId(name,id);
            }else if (request.getParameter("saleDate")!=null&&(!request.getParameter("saleDate").equals(""))){
                time1=request.getParameter("saleDate");
                Integer a = Integer.parseInt(time1.substring(8, 10),10);// substring() 方法用于提取字符串中介于两个指定下标之间的字符。,parseInt() 方法用于将字符串参数作为有符号的十进制整数进行解析
                a1=""+(a+1);
                StringBuilder day = new StringBuilder((time1)); //StringBuilder类也代表可变字符串对象。
                day.replace(8,10,a1);
                time2 = day.toString();
                list=ordersService.selectOrderByDateAndId(time1,time2,id);
            }
            model.addAttribute("lists",list);
            return "qiantai/order";
        }else {
            return "qiantai/error";
        }


    }

    //前台查询订单情况
    @RequestMapping("/qiantai/orderList")
    public String toOrdersList(Model model,HttpSession session){
        if (session.getAttribute("user")!=null){
            Users user = (Users) session.getAttribute("user");
            Integer id = user.getId();
            Collection<OrdersDTO> ordersDTOList = ordersService.selectOrdersByUserId(id);
            model.addAttribute("qtorder_list",ordersDTOList);
            return "qiantai/orderList";
        }else {
            return "qiantai/error";
        }
    }
    //用户确认订单送达或取消订单
    @RequestMapping("/qiantai/OrderConfirm")
    public String QTOrdersConfirm(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        String reqtype=request.getParameter("reqtype");
        if(reqtype.equals("delivery")){
            ordersService.updateDeliveryById(id);
        }else if(reqtype.equals("del")){
            ordersService.deleteById(id);
        }else{
            System.out.println("错误");
        }
        return "redirect:/qiantai/orderList";
    }
    //用户查询已派送和未派送订单情况
    @RequestMapping("/qiantai/OrderdeliveryOrNot")
    public String OrderdeliveryOrNot(HttpServletRequest request,Model model){
        String delivery = request.getParameter("delivery");
        HttpSession session = request.getSession();
        if (session.getAttribute("user")!=null){
            Users user = (Users) session.getAttribute("user");
            Integer id = user.getId();//用户
            int d = Integer.parseInt(delivery);//是否派送
            List<OrdersDTO> list = ordersService.selectOrderByDelivery(d,id);
            System.out.println(id);
            System.out.println(d);
            model.addAttribute("lists",list);
            return "qiantai/order";
        }else {
            return "qiantai/error";
        }
    }

    //    跳转到our页面
    @RequestMapping("/qiantai/our")
    public String toQiantaiOur(){
        return "qiantai/our";
    }

    //    跳转到carry页面
    @RequestMapping("/qiantai/carry")
    public String toQiantaiCarry(){
        return "qiantai/carry";
    }
}
