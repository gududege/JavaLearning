package com.model;

import com.utils.Util;

import java.util.Calendar;

/**
 * <p><em>订单实体类：</em>包含
 * <ul>
 * <li>uuid</li>
 * <li>name</li>
 * <li>phone</li>
 * <li>user_create_date</li>
 * <li>user_modify_date</li>
 * </ul></p>
 * <p>注意UUID使用{@link Util#generateUUID()}方法生成</p>
 *
 * @author Liu
 */
public class User {

    private String uuid;

    private String name;

    private long phone;

    private String password;

    private Calendar user_create_date;

    private Calendar user_modify_date;

    /**
     * 用户注册时创建对象用，自动生成一个UUID
     *
     * @param name     用户名
     * @param password 密码
     * @param phone    手机号
     */
    public User(String name, String password, long phone) {
        this.uuid = Util.generateUUID();
        this.name = name;
        this.password = password;
        this.phone = phone;
    }


    /**
     * 用户登录时创建对象用
     *
     * @param name     用户名
     * @param password 密码
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * 用户登录时创建对象用
     *
     * @param phone    手机号
     * @param password 密码
     */
    public User(long phone, String password) {
        this.password = password;
        this.phone = phone;
    }

    public User() {
    }

    @Override
    public String toString() {
        return  "姓名='" + name + '\'' +
                ", 电话=" + phone +
                ", 密码=" + password +
                "\n";
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Calendar getUser_create_date() {
        return user_create_date;
    }

    public void setUser_create_date(Calendar user_create_date) {
        this.user_create_date = user_create_date;
    }

    public Calendar getUser_modify_date() {
        return user_modify_date;
    }

    public void setUser_modify_date(Calendar user_modify_date) {
        this.user_modify_date = user_modify_date;
    }


}
