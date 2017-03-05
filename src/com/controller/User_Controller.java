package com.controller;


import com.model.Auto_Table;
import com.model.OrderList;
import com.model.OrderList_Table;
import com.model.User;
import com.model.User_Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Liu
 */
public class User_Controller extends Controller {

    /**
     * 当前登录的用户帐户
     */
    private static User user;

    private static User_Table ut = new User_Table();

    private static OrderList_Table ot = new OrderList_Table();

    private static Auto_Table at = new Auto_Table();

    private static Scanner in = new Scanner(System.in);

    /**
     * 用户登陆验证
     *
     * @param phone    手机号
     * @param password 密码
     * @return
     * @throws SQLException
     */
    public boolean login(long phone, String password) throws SQLException {
        boolean bool = false;
        if (phone != 0) {
            if (ut.queryValueIsExists("phone", String.valueOf(phone))) {
                User corruser = ut.query("phone", "=", String.valueOf(phone));
                if (password.equals(corruser.getPassword())) {
                    bool = true;
                    user = corruser;
                    corruser = null;
                }
            }
        }
        password = null;
        return bool;
    }

    /**
     * 用户登陆验证
     *
     * @param name     用户名
     * @param password 密码
     * @return
     * @throws SQLException
     */
    public boolean login(String name, String password) throws SQLException {
        User_Table ut = new User_Table();
        User corruser;
        boolean bool = false;
        if (name != null) {
            if (ut.queryValueIsExists("name", "'" + name + "'")) {
                corruser = ut.query("name", " = ", "'" + name + "'");
                if (password.equals(corruser.getPassword())) {
                    bool = true;
                    user = corruser;
                    corruser = null;
                }
            }
        }
        password = null;
        corruser = null;
        ut = null;
        return bool;
    }


    /**
     * 新用户注册
     *
     */
    public void register() throws SQLException {

        String name;
        int count = 0;
        do {
            if (++count == 1) {
                System.out.println("请输入用户名：");
            }
            if (count > 1) {
                System.out.println("用户名已被占用，请重新输入：");
            }
            int count1 = 0;
            do {
                if (count1++ > 1) {
                    System.out.println("用户名不能为空，请重新输入：");
                }
                name = in.next();
            } while (name == null);
        } while (ut.queryValueIsExists("name", "'" + name + "'"));

        long phone;
        count = 0;
        boolean numberNotMatch = false;
        do {
            if (numberNotMatch) {
                System.out.println("输入的手机号格式不对，请重新输入");
            } else {
                if (++count == 1) {
                    System.out.println("请输入新手机号");
                }
                if (count > 1) {
                    System.out.println("该手机号已被注册，请重新输入");
                }
            }
            phone = in.nextLong();
            if (String.valueOf(phone).length() != 11) {
                numberNotMatch = true;
            }
        } while (ut.queryValueIsExists("phone", String.valueOf(phone)) || numberNotMatch);

        String password = null;
        count = 0;
        do {
            if (++count == 1) {
                System.out.println("请输入密码：");
            }
            if (count > 1) {
                System.out.println("密码不能为空，请重新输入：");
            }
            password = in.next();
        } while (password == null);

        user = new User(name, password, phone);
        ut.add(user);
    }

    /**
     *
     */
    public void order() throws SQLException {
        System.out.println("当前车辆信息为:\n");
        printALLInfo(at);

    }

    /**
     * 查询当前用户的历史订单
     */
    public void queryHistoryOrderList() throws SQLException {
        ArrayList<OrderList> orderLists = new ArrayList<>();
        orderLists = ot.getObjects("user_name", "=", "'" + user.getName() + "'");
        System.out.println("查询当前用户的历史订单：");
        for (OrderList ol : orderLists) {
            System.out.println(ol.toString());
        }
    }

    /**
     * 修改当前账户信息
     */
    public void editAccountInfo() throws SQLException {
        String content = "当前账户信息为：\n"
                + user.toString()
                + "请输入要修改的字段名如（name、phone、password）\n"
                + "输入submit提交,输入quit或任意键退出修改";
        System.out.println(content);
        while (true) {
            String str = in.next();
            if (str.equals("quit")) {
                break;
            }
            switch (str) {
                case "name":
                    String name;
                    int count = 0;
                    do {
                        if (++count == 1) {
                            System.out.println("请输入新名字");
                        }
                        if (count > 1) {
                            System.out.println("新名字已被占用，请重新输入");
                        }
                        name = in.next();
                    }
                    while (ut.queryValueIsExists("name", "'" + name + "'"));
                    user.setName(name);
                    break;
                case "phone":
                    Long phone;
                    count = 0;
                    boolean numberNotMatch = false;
                    do {
                        if (numberNotMatch) {
                            System.out.println("输入的手机号格式不对，请重新输入");
                        } else {
                            if (++count == 1) {
                                System.out.println("请输入新手机号");
                            }
                            if (count > 1) {
                                System.out.println("该手机号已被注册，请重新输入");
                            }
                        }
                        phone = in.nextLong();
                        if (String.valueOf(phone).length() != 11) {
                            numberNotMatch = true;
                        }
                    } while (ut.queryValueIsExists("phone", String.valueOf(phone)) || numberNotMatch);
                    user.setPhone(phone);
                    break;
                case "password":
                    System.out.println("请输入新密码");
                    String password = in.next();
                    user.setPassword(password);
                    break;
            }

            if (str.equals("submit")) {
                ut.update(user);
                break;
            }
            System.out.println("请继续输入要修改的字段名如（name、phone、password）,输入submit提交");
        }
        System.out.println("修改成功，返回主菜单");
    }
}
