package com.zm.aidian.dto;

import com.zm.aidian.dao.Users;
import lombok.Data;
import org.apache.catalina.User;

@Data
public class OrdersDTO {
    private Integer id; // 订单id
    private Integer userid; // 用户id
    private String username; // 用户名
    private String phone; // 联系方式
    private String address; // 住址
    private String menuname; //菜品名称
    private Integer menusum; //订购数量
    private float price1; //订购单价
    private String times;//订购时间
    private Integer delivery;//是否派送





}
