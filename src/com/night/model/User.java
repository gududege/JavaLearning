package com.night.model;

import com.night.utils.Util;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;

/**
 * <p><em>订单实体类：</em>包含
 * <ul>
 * <li>uuid</li>
 * <li>name</li>
 * <li>phone</li>
 * <li>user_Create_Date</li>
 * <li>user_Modify_Date</li>
 * </ul></p>
 * <p>注意UUID使用{@link Util#generateUUID()}方法生成</p>
 *
 * @author Liu
 */
@Data
@NoArgsConstructor
public class User implements Serializable{

    private String UUID;

    private String name;

    private long phone;

    private String password;

    /**
     * 用户注册时创建对象用，自动生成一个UUID
     *
     * @param name     用户名
     * @param password 密码
     * @param phone    手机号
     */
    public User(String name, String password, long phone) {
        this.UUID = Util.generateUUID();
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

    @Override
    public String toString() {
        return "User{" +
                "UUID='" + UUID + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                '}';
    }
}
