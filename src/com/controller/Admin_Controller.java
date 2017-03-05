package com.controller;

import com.model.Auto_Table;
import com.model.OrderList_Table;
import com.model.User;
import com.model.User_Table;

import java.sql.SQLException;
import java.util.Scanner;


public class Admin_Controller extends Controller {

    protected static final String Admin_name = "admin";

    protected static final String Admin_password = "admin";

    protected static final User Admin = new User(Admin_name, Admin_password);

    private static User_Table ut = new User_Table();

    private static OrderList_Table ot = new OrderList_Table();

    private static Auto_Table at = new Auto_Table();

    private static Scanner in = new Scanner(System.in);

    /**
     * 管理员登陆验证
     *
     * @param name     用户输入的用户名
     * @param password 用户输入的密码
     * @return
     */
    public boolean login(String name, String password) {
        boolean bool = false;
        if (Admin_name.equals(name) && Admin_password.equals(password)) {
            bool = true;
        }
        return bool;
    }

    /**
     * 查询数据库表中信息
     */
    public void searchDatabase() throws SQLException {
        while (true) {
            System.out.println("请输入要查询的表：\n" +
                    "[1]User表\t[2]Auto表\t[3]OrderList表\t[4]退出查询,返回上一菜单");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    printALLInfo(ut);
                    break;
                case 2:
                    printALLInfo(at);
                    break;
                case 3:
                    printALLInfo(ot);
                    break;
            }
            if (i == 4) {
                break;
            }
        }

    }
}
