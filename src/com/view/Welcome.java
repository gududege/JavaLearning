package com.view;

import com.controller.Controller;
import com.model.Auto_Table;
import com.model.OrderList_Table;
import com.model.User_Table;
import com.utils.Util;

import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) throws SQLException {

        if (!Util.istableexist("autolist") || !Util.istableexist("orderlist")
                || !Util.istableexist("user")) {
            Auto_Table at = new Auto_Table();
            OrderList_Table ot = new OrderList_Table();
            User_Table ut = new User_Table();
            at.dropTable();
            ot.dropTable();
            ut.dropTable();
            at.createTable();
            ot.createTable();
            ut.createTable();
            System.out.println("系统初始化完成");
            at = null;
            ot = null;
            ut = null;
        }

        Scanner in = new Scanner(System.in);
        System.out.println("欢迎进入滴答租车系统，请选择入口：");
        System.out.print("1、我是用户\t");
        System.out.println("2、我是管理员");
        do {
            switch (in.next()) {
                case "1":
                    new User_View().run();
                    break;
                case "2":
                    new Admin_View().run();
                    break;
                case "exit":
                    Controller.exit();
                default:
                    System.out.println("退出请输入exit：");
            }
        } while (true);
    }
}
