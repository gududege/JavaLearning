package com.night.model;

import com.night.utils.Util;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * <p><em>订单实体类：</em>包含
 * <ul>
 *     <li>orderNumber</li>
 *     <li>userUUID</li>
 *     <li>orderCreateDate</li>
 *     <li>orderModifyDate</li>
 *     <li>number</li>
 *     <li>autoIds</li>
 *     <li>autoNumbers</li>
 *     <li>dayNumbers</li>
 *     <li>beginDate</li>
 *     <li>price</li>
 * </ul></p>
 * <p>注意order_number使用{@link Util#generateOrderNumber()}方法生成</p>
 * @author Liu
 */
@Data
@NoArgsConstructor
public class OrderList {

    private int orderNumber; //订单号

    private String userUUID; //订单用户

    private Date orderCreateDate; //订单生成日期

    private Date orderModifyDate; //订单被修改日期

    private int autoId; //预订车辆识别代号

    private int autoNumber; //预订车型数量

    private int dayNumber; //预定天数

    private Date beginDate;  //开始日期

    private boolean payed;
    
    private int price; //总价
    
    public OrderList(int orderNumber, String userUUID, Date orderModifyDate,
                     int autoId, int autoNumber, int dayNumber, Date beginDate, int price) {
        this.orderNumber = orderNumber;
        this.userUUID = userUUID;
        this.orderModifyDate = orderModifyDate;
        this.autoId = autoId;
        this.autoNumber = autoNumber;
        this.dayNumber = dayNumber;
        this.beginDate = beginDate;
        this.payed = false;
        this.price = price;
    }
}
