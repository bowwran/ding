package com.zm.aidian;

import com.zm.aidian.dao.*;
import com.zm.aidian.dto.MenusDTO;
import com.zm.aidian.dto.OrdersDTO;
import com.zm.aidian.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class AidianApplicationTests {

   @Autowired
    DataSource dataSource;


   @Autowired
   MenusService menusService;
   @Autowired
    TypeService typeService;
   @Test
   public void test01(){
       List<MenusDTO> menus = menusService.selectAllMenusDTO();
       System.out.println(menus);
   }
    @Test
    public void test02(){
        List<Types> types = typeService.selectAllType();
        System.out.println(types);
    }
    @Test
    public void test03(){
        Menus menus = menusService.selectMenuById(15);
        System.out.println(menus);
    }
    @Test
    public void test4(){
        menusService.addMenu(new Menus(22,"五香驴肉",10,"驴肉","暂无",26,0,0,24,"img/refined_rice.gif"));
        System.out.println(menusService.selectMenuById(22));
    }
    @Test
    public void test04(){
       menusService.updateMenu(new Menus(17,"五香驴肉",10,"驴肉","暂无",26,0,0,24,"img/refined_rice.gif"));
        System.out.println(menusService.selectMenuById(17));
    }

    @Autowired
    OrdersService ordersService;
    @Test
    public void test05(){
        List<OrdersDTO> ordersDTOS = ordersService.selectAllOrdersDAO();
        System.out.println(ordersDTOS);
    }
    @Test
    public void test06(){
        ordersService.updateDeliveryById(16);
        System.out.println(ordersService.selectAllOrdersDAO());
    }
    @Test
    public void test07(){
        System.out.println(ordersService.selectOrderByDate("2021-05-19","2021-05-20"));

    }
    @Autowired
    AdminService adminService;
    @Test
    public void test08(){
        System.out.println(adminService.selectNameByPwd("苏","123"));
    }
    @Test
    public void test09(){
        adminService.updateAdminPwd(new Admin(2,"苏","123456"));
    }
    @Autowired
    UsersService usersService;
    @Test
    public void test10(){
        System.out.println(ordersService.selectOrderByDelivery(1,2));
    }
    @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}

