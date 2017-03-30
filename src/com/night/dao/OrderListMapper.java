package com.night.dao;


import com.night.model.Auto;
import com.night.model.OrderList;

import java.util.List;

public interface OrderListMapper extends Mapper{

    OrderList selectOneByOrderNumber(int order_number);

    List<OrderList> selectList();

    void saveOrderList(OrderList OrderList);

    void addOrderList(OrderList OrderList);

    void deleteOrderList(OrderList OrderList);

    void createTable();

}
