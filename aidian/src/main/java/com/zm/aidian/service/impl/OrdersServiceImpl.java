package com.zm.aidian.service.impl;


import com.zm.aidian.Mapper.OrdersMapper;
import com.zm.aidian.dao.Orders;
import com.zm.aidian.dto.OrdersDTO;
import com.zm.aidian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<OrdersDTO> selectAllOrdersDAO() {
        return ordersMapper.selectAllOrdersDAO();
    }

    @Override
    public Integer updateDeliveryById(int id) {
        return ordersMapper.updateDeliveryById(id);
    }

    @Override
    public Integer deleteById(int id) {
        return ordersMapper.deleteById(id);
    }

    @Override
    public List<OrdersDTO> selectOrdersByUserId(Integer id) {
        return ordersMapper.selectOrdersByUserId(id);
    }

    @Override
    public List<OrdersDTO> selectOrdersByMenusName(String name) {
        return ordersMapper.selectOrdersByMenusName(name);
    }

    @Override
    public List<OrdersDTO> selectOrderByDate(String time1, String time2) {
        return ordersMapper.selectOrderByDate(time1,time2);
    }

    @Override
    public Integer addOrder(Orders orders) {
        return ordersMapper.addOrder(orders);
    }

    @Override
    public List<OrdersDTO> selectOrderByDelivery(Integer delivery,Integer id) {
        List<OrdersDTO> ordersDTOS = ordersMapper.selectOrderByDelivery(delivery,id);
        return ordersDTOS;
    }

    @Override
    public List<OrdersDTO> selectOrdersByMenusNameAndId(String name, Integer id) {
        return ordersMapper.selectOrdersByMenusNameAndId(name, id);
    }

    @Override
    public List<OrdersDTO> selectOrderByDateAndId(String time1, String time2, Integer id) {
        return ordersMapper.selectOrderByDateAndId(time1, time2, id);
    }
}
