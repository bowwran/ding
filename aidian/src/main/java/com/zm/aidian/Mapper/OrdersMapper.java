package com.zm.aidian.Mapper;

import com.zm.aidian.dao.Orders;
import com.zm.aidian.dto.OrdersDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Component
public interface OrdersMapper {
//    查询所有订单
    List<OrdersDTO> selectAllOrdersDAO();

//    通过id更新派送情况
    Integer updateDeliveryById(@PathVariable("id")int id);

    //    通过id删除派送情况
    Integer deleteById(@PathVariable("id")int id);

//    通过用户id查询全部订单情况
    List<OrdersDTO> selectOrdersByUserId(@PathVariable("id")Integer id);
//    通过菜品名称查询全部订单情况
    List<OrdersDTO> selectOrdersByMenusName(String name);
//    通过日期查询全部订单情况
    List<OrdersDTO> selectOrderByDate(String time1,String time2);

//    前台增加订单
    Integer addOrder(Orders orders);
//    通过id和派送情况查询订单情况
    List<OrdersDTO> selectOrderByDelivery(Integer delivery,Integer id);

    //    通过菜品名称查询全部订单情况
    List<OrdersDTO> selectOrdersByMenusNameAndId(String name,Integer id);
    //    通过日期查询全部订单情况
    List<OrdersDTO> selectOrderByDateAndId(String time1,String time2,Integer id);
}
