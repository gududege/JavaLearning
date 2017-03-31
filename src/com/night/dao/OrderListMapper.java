package com.night.dao;


import com.night.model.OrderList;

import java.util.List;

public interface OrderListMapper{

    OrderList getOneByOrderNumber(int order_number);

    List<OrderList> getList();

    void saveOrderList(OrderList OrderList);

    void addOrderList(OrderList OrderList);

    void deleteOrderList(OrderList OrderList);

    void createTable();

}
