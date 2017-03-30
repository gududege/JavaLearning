package com.night.model;

import com.night.utils.Util;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *汽车实体类：包含
 * <ul>
 *     <li>id</li>
 *     <li>name</li>
 *     <li>type</li>
 *     <li>quality</li>
 *     <li>weightCapacity</li>
 *     <li>passengerNumber</li>
 *     <li>dayPrice</li>
 * </ul>
 * @author Liu
 */

@Data
@NoArgsConstructor
public class Auto {

    //写入到数据库时自动赋值
    public int id; //品牌id
    
    public String name; //品牌名称
    
    public String type; //汽车类型
    
    public int quality; //车型数量
    
    public int weightCapacity; //额定载货量
    
    public int passengerNumber; //额定载客量
    
    public int dayPrice; //单价

    public Auto(String name, String type, int quality, int weightCapacity, int passengerNumber, int dayPrice) {
        this.name = name;
        this.type = type;
        this.quality = quality;
        this.weightCapacity = weightCapacity;
        this.passengerNumber = passengerNumber;
        this.dayPrice = dayPrice;
    }
}