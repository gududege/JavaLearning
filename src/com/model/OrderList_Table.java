package com.model;

import com.utils.Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单信息表：
 * 表定义(order_number不可更改)<ol>
 * <li>order_number int unsigned primary key</li>
 * <li>user_name varchar(10) not NULL</li>
 * <li>order_create_date timestamp not null</li>
 * <li>order_modify_date timestamp not null default current_timestamp on update current_timestamp,</li>
 * <li>auto_ids varchar(20) not NULL</li>
 * <li>auto_numbers varchar(20) not NULL</li>
 * <li>day_numbers varchar(20) not NULL</li>
 * <li>begin_date date not NULL</li>
 * <li>price smallint unsigned not NULL</li>
 * </ol>
 * 1.订单号（结构：年+月+日+当日订单序号）每日最多99单<br/>
 * 2.订单创建日期：自1970年的毫秒偏移量，用varchar存储<br/>
 *
 * @author Liu
 * @// TODO: 2017/2/19  1、测试每个功能块 2、修改表中timestamp的定义
 */
public class OrderList_Table implements Table {

    private static Connection connection;

    private static String sql;

    private static final String tablename = "orderlist";

    private final String[] columns = {"order_number", "user_name", "order_create_date", "order_modify_date", "auto_ids", "auto_numbers", "day_numbers",
            "begin_date", "price"};

    @Override
    public boolean createTable() throws SQLException {
        connection = DB.getConnection();
        sql = new StringBuffer().append("create table if not exists " + tablename + "(")
                .append(" order_number int unsigned primary key,")
                .append(" user_name varchar(10) not NULL,")
                .append(" order_create_date timestamp not null default current_timestamp(),")
                .append(" order_modify_date timestamp not null default current_timestamp() on update current_timestamp(),")
                .append(" auto_ids varchar(20) not NULL,")
                .append(" auto_numbers varchar(20) not NULL,")
                .append(" day_numbers varchar(20) not NULL,")
                .append(" begin_date date not NULL, ")
                .append(" price smallint unsigned not NULL)")
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        sql = null;
        pstm.close();
        connection.close();
        boolean bool = false;
        if (Util.istableexist(tablename)) {
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean dropTable() throws SQLException {
        connection = DB.getConnection();
        sql = "drop table if exists " + tablename;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        sql = null;
        pstm.close();
        connection.close();
        boolean bool = true;
        if (Util.istableexist(tablename)) {
            bool = false;
        }
        return bool;
    }

    @Override
    public boolean add(Object object) throws SQLException {
        OrderList orderList = (OrderList) object;
        sql = new StringBuffer().append("insert into " + tablename)
                .append("( order_number,user_name,auto_ids,auto_numbers,day_numbers,begin_date,price)")
                .append(" values (?,?,?,?,?,?,?)")
                .toString();
        connection = DB.getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int i = 1;
        pstm.setInt(i++, orderList.getOrder_number());
        pstm.setString(i++, orderList.getUser_name());
        pstm.setString(i++, Util.getString(orderList.getAuto_ids()));
        pstm.setString(i++, Util.getString(orderList.getAuto_numbers()));
        pstm.setString(i++, Util.getString(orderList.getDay_numbers()));
        pstm.setDate(i++, new Date(orderList.getBegin_date().getTimeInMillis()));
        pstm.setInt(i++, orderList.getPrice());
        pstm.execute();
        boolean bool = false;
        sql = "SELECT * FROM " + tablename + "where id = " + orderList.getOrder_number();
        pstm = connection.prepareStatement(sql);
        if (pstm.execute()) {
            bool = true;
        }
        sql = null;
        pstm.close();
        connection.close();
        return bool;
    }

    /**
     * 利用{@link Util#getString(ArrayList)}方法将订单车型、数量、天数转换为“1|1|1|1”型的字符串
     */
    @Override
    public void update(Object object) throws SQLException {
        OrderList orderList = (OrderList) object;
        connection = DB.getConnection();
        sql = new StringBuffer().append("update table " + tablename)
                .append(" set user_name=?,auto_ids=?, ")
                .append(" auto_numbers=?,day_numbers=?,begin_date=?,price=?")
                .append(" where order_number= " + orderList.getOrder_number())
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int i = 1;
        pstm.setString(i++, orderList.getUser_name());
        pstm.setString(i++, Util.getString(orderList.getAuto_ids()));
        pstm.setString(i++, Util.getString(orderList.getAuto_numbers()));
        pstm.setString(i++, Util.getString(orderList.getDay_numbers()));
        pstm.setDate(i++, new Date(orderList.getBegin_date().getTimeInMillis()));
        pstm.setInt(i++, orderList.getPrice());
        pstm.execute();
        sql = null;
        pstm.close();
        connection.close();
    }

    @Override
    public Object[] queryField(String field, String condition, String relation, String value) throws SQLException {
        Object[] objects = new Object[10];
        connection = DB.getConnection();
        sql = "SELECT " + field + " FROM " + tablename + " WHERE " + condition + relation + value;
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
            if (count == 10) {
                break;
            }
            objects[count] = rs.getObject(field);
        }
        sql = null;
        pstm.close();
        connection.close();
        return objects;
    }

    @Override
    public boolean queryValueIsExists(String field, String value) throws SQLException {
        boolean bool = false;
        connection = DB.getConnection();
        sql = "SELECT " + field + " FROM " + tablename + " WHERE " + field + "=" + value;
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            bool = true;
        }
        sql = null;
        pstm.close();
        connection.close();
        return bool;
    }

    @Override
    public OrderList query(String condition, String relation, String value) throws SQLException {
        Map<String,String> map = new HashMap<>();
        map.put("condition",condition);
        map.put("relation",relation);
        map.put("value",value);
        return query(map);
    }

    @Override
    public OrderList query(Map<String, String> map) throws SQLException {
        OrderList orderList = new OrderList();
        Calendar cal = Calendar.getInstance();
        connection = DB.getConnection();
        sql = new StringBuffer().append("select * from " + tablename + " where ")
                .append(map.get("condition") + map.get("relation") + map.get("value"))
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        orderList.setOrder_number(rs.getInt("order_number"));
        orderList.setUser_name(rs.getString("user_name"));
        cal.setTimeInMillis(rs.getTimestamp("order_create_date").getTime());
        orderList.setOrder_create_date(cal);
        cal.setTimeInMillis(rs.getTimestamp("order_modify_date").getTime());
        orderList.setOrder_modify_date(cal);
        orderList.setAuto_ids(Util.getList(rs.getString("auto_ids")));
        orderList.setAuto_numbers(Util.getList(rs.getString("auto_numbers")));
        orderList.setDay_numbers(Util.getList(rs.getString("day_numbers")));
        cal.setTime(rs.getTime("begin_date"));
        orderList.setBegin_date(cal);
        orderList.setPrice(rs.getInt("price"));
        sql = null;
        cal = null;
        rs.close();
        pstm.close();
        connection.close();
        return orderList;
    }

    @Override
    public ArrayList<OrderList> getObjects(String condition, String relation, String value) throws SQLException {
        Map<String,String> map = new HashMap<>();
        map.put("condition",condition);
        map.put("relation",relation);
        map.put("value",value);
        return getObjects(map);
    }

    @Override
    public ArrayList<OrderList> getObjects(Map<String, String> map) throws SQLException {
        ArrayList<OrderList> orderLists = new ArrayList<>();
        OrderList orderList = null;
        Calendar cal = Calendar.getInstance();
        connection = DB.getConnection();
        sql = new StringBuffer().append("select * from " + tablename + " where ")
                .append(map.get("condition") + map.get("relation") + map.get("value"))
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            orderList = new OrderList();
            orderList.setOrder_number(rs.getInt("order_number"));
            orderList.setUser_name(rs.getString("user_name"));
            cal.setTimeInMillis(rs.getTimestamp("order_create_date").getTime());
            orderList.setOrder_create_date(cal);
            cal.setTimeInMillis(rs.getTimestamp("order_modify_date").getTime());
            orderList.setOrder_modify_date(cal);
            orderList.setAuto_ids(Util.getList(rs.getString("auto_ids")));
            orderList.setAuto_numbers(Util.getList(rs.getString("auto_numbers")));
            orderList.setDay_numbers(Util.getList(rs.getString("day_numbers")));
            cal.setTime(rs.getTime("begin_date"));
            orderList.setBegin_date(cal);
            orderList.setPrice(rs.getInt("price"));
            orderLists.add(orderList);
            orderList = null;
        }
        sql = null;
        cal = null;
        rs.close();
        pstm.close();
        connection.close();
        return orderLists;
    }
}
