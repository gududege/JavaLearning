package com.night.model;

import com.night.utils.Util;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * <p><em>订单实体类：</em>包含
 * <ul>
 *     <li>order_Number</li>
 *     <li>user_Name</li>
 *     <li>order_Create_Date</li>
 *     <li>order_Modify_Date</li>
 *     <li>number</li>
 *     <li>auto_Ids</li>
 *     <li>auto_Numbers</li>
 *     <li>day_Numbers</li>
 *     <li>begin_Date</li>
 *     <li>price</li>
 * </ul></p>
 * <p>注意order_number使用{@link Util#generateOrderNumber()}方法生成</p>
 * @author Liu
 */
@Data
@NoArgsConstructor
public class OrderList {

    private int order_Number; //订单号

    private String user_Name; //订单用户

    private Calendar order_Create_Date; //订单生成日期

    private Calendar order_Modify_Date; //订单被修改日期

    private int number; //本订单车型数

    private ArrayList<Integer> auto_Ids; //预订车辆识别代号

    private ArrayList<Integer> auto_Numbers; //预订车型数量

    private ArrayList<Integer> day_Numbers; //预定天数

    private Calendar begin_Date;  //开始日期

    private int price; //总价

    public OrderList(String user_Name, int number, ArrayList<Integer> auto_Ids,
                     ArrayList<Integer> auto_Numbers, ArrayList<Integer> day_Numbers,
                     Calendar begin_Date, int price) throws Exception {
        if (number!= auto_Ids.size()|| auto_Ids.size()!= auto_Numbers.size()|| auto_Numbers.size()!= day_Numbers.size()){
            throw new Exception("该订单错误，请检查订购车型、数量、天数是否一致");
        }
        this.order_Number = Util.generateOrderNumber(); //生成*位订单号
        this.order_Create_Date =Calendar.getInstance();
        this.user_Name = user_Name;
        this.number= auto_Ids.size();
        this.auto_Ids = auto_Ids;
        this.auto_Numbers = auto_Numbers;
        this.day_Numbers = day_Numbers;
        this.begin_Date = begin_Date;
        this.price = price;
    }

    
    @Override
    public String toString() {
        return "OrderList{" +
                "order_Number=" + order_Number +
                ", user_Name='" + user_Name + '\'' +
                ", order_Create_Date=" + order_Create_Date +
                ", order_Modify_Date=" + order_Modify_Date +
                ", number=" + number +
                ", auto_Ids=" + auto_Ids +
                ", auto_Numbers=" + auto_Numbers +
                ", day_Numbers=" + day_Numbers +
                ", begin_Date=" + begin_Date +
                ", price=" + price +
                "\n";
    }
}
