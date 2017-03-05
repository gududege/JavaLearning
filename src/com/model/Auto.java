package com.model;

import com.utils.Util;

/**
 *汽车实体类：包含
 * <ul>
 *     <li>id</li>
 *     <li>name</li>
 *     <li>type</li>
 *     <li>quality</li>
 *     <li>weight</li>
 *     <li>passengernumber</li>
 *     <li>dayprice</li>
 * </ul>
 * @author Liu
 */
public class Auto {
    
    public int id; //品牌id
    
    public String name; //品牌名称
    
    public String type; //汽车类型
    
    public int quality; //车型数量
    
    public int weight; //额定载货量
    
    public int passengernumber; //额定载客量
    
    public int dayprice; //单价

    public Auto() {

    }
    
    public Auto(String name, String type, int quality, int weight, int passengernumber, int dayprice) {
        this.id = Util.generateAutoId();
        this.name = name;
        this.type = type;
        this.quality = quality;
        this.weight = weight;
        this.passengernumber = passengernumber;
        this.dayprice = dayprice;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPassengernumber() {
        return passengernumber;
    }

    public void setPassengernumber(int number) {
        this.passengernumber = number;
    }

    public int getDayprice() {
        return dayprice;
    }

    public void setDayprice(int price) {
        this.dayprice = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quality=" + quality +
                ", weight=" + weight +
                ", passengernumber=" + passengernumber +
                ", dayprice=" + dayprice +
                "\n";
    }
}