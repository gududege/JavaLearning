package com.view;

import com.controller.Admin_Controller;
import com.controller.Controller;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * @// TODO: 2017/3/4 完成编辑数据库模块 
 */
public class Admin_View {

    private static Admin_View av = new Admin_View();

    private static Admin_Controller ac = new Admin_Controller();

    private static Scanner in = new Scanner(System.in);

    public void run() throws SQLException {
        do {
            System.out.println("请输入管理员帐号：\t");
            String admin = in.next();
            if (admin.equals("exit")) {
                Controller.exit();
            }
            System.out.println("请输入管理员密码: \t");
            String password = in.next();
            Admin_Controller ac = new Admin_Controller();
            if (ac.login(admin, password)) {
                av.menu();
            }
            System.out.println("输入的帐号密码有误，请重新输入，退出请输入exit");
        } while (true);
    }

    /**
     * 管理员功能菜单
     */
    public void menu() throws SQLException {
        final String content = "主菜单：\n"
                + "[e/edit]\t编辑数据库信息\n"
                + "[s/search]\t查询数据库信息\n"
                + "[b/back]\t返回上一级\n"
                + "[q/quit]\t退出系统\n";
        while (true){
            System.out.println(content);
            switch (in.next().substring(0, 1).toUpperCase()) {
                case "E":
                    break;
                case "S":
                    ac.searchDatabase();
                    av.menu();
                    break;
                case "B":
                    av.run();
                    break;
                case "Q":
                    Controller.exit();
            }
        }
    }
}
