package com.model;

import com.utils.Util;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * <p><em>订单实体类：</em>包含
 * <ul>
 *     <li>order_number</li>
 *     <li>user_name</li>
 *     <li>order_create_date</li>
 *     <li>order_modify_date</li>
 *     <li>number</li>
 *     <li>auto_ids</li>
 *     <li>auto_numbers</li>
 *     <li>day_numbers</li>
 *     <li>begin_date</li>
 *     <li>price</li>
 * </ul></p>
 * <p>注意order_number使用{@link Util#generateOrderNumber()}方法生成</p>
 * @author Liu
 */
public class OrderList {

    private int order_number; //订单号

    private String user_name; //订单用户

    private Calendar order_create_date; //订单生成日期

    private Calendar order_modify_date; //订单被修改日期

    private int number; //本订单车型数

    private ArrayList<Integer> auto_ids; //预订车辆识别代号

    private ArrayList<Integer> auto_numbers; //预订车型数量

    private ArrayList<Integer> day_numbers; //预定天数

    private Calendar begin_date;  //开始日期

    private int price; //总价

    public OrderList()  {
    }

    public OrderList(String user_name, int number,ArrayList<Integer> auto_ids,
                     ArrayList<Integer> auto_numbers, ArrayList<Integer> day_numbers,
                     Calendar begin_date, int price) throws Exception {
        if (number!=auto_ids.size()||auto_ids.size()!=auto_numbers.size()||auto_numbers.size()!=day_numbers.size()){
            throw new Exception("该订单错误，请检查订购车型、数量、天数是否一致");
        }
        this.order_number = Util.generateOrderNumber(); //生成*位订单号
        this.order_create_date=Calendar.getInstance();
        this.user_name = user_name;
        this.number=auto_ids.size();
        this.auto_ids = auto_ids;
        this.auto_numbers = auto_numbers;
        this.day_numbers = day_numbers;
        this.begin_date = begin_date;
        this.price = price;
    }
    
    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }
    
    @Override
    public String toString() {
        return "OrderList{" +
                "order_number=" + order_number +
                ", user_name='" + user_name + '\'' +
                ", order_create_date=" + order_create_date +
                ", order_modify_date=" + order_modify_date +
                ", number=" + number +
                ", auto_ids=" + auto_ids +
                ", auto_numbers=" + auto_numbers +
                ", day_numbers=" + day_numbers +
                ", begin_date=" + begin_date +
                ", price=" + price +
                "\n";
    }
    
    public Calendar getOrder_modify_date() {
        return order_modify_date;
    }

    public void setOrder_modify_date(Calendar order_modify_date) {
        this.order_modify_date = order_modify_date;
    }

    public Calendar getOrder_create_date() {
        return order_create_date;
    }

    public void setOrder_create_date(Calendar order_create_date) {
        this.order_create_date = order_create_date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOrder_number() {
        return order_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public ArrayList<Integer> getAuto_ids() {
        return auto_ids;
    }

    public void setAuto_ids(ArrayList<Integer> auto_ids) {
        this.auto_ids = auto_ids;
    }

    public ArrayList<Integer> getAuto_numbers() {
        return auto_numbers;
    }

    public void setAuto_numbers(ArrayList<Integer> auto_numbers) {
        this.auto_numbers = auto_numbers;
    }

    public ArrayList<Integer> getDay_numbers() {
        return day_numbers;
    }

    public void setDay_numbers(ArrayList<Integer> day_numbers) {
        this.day_numbers = day_numbers;
    }

    public Calendar getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Calendar begin_date) {
        this.begin_date = begin_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
