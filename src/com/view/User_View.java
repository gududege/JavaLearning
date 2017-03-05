package com.view;

import com.controller.Controller;
import com.controller.User_Controller;
import com.utils.Util;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * @// TODO: 2017/3/4 完成order模块
 */
public class User_View {

    private static Scanner in = new Scanner(System.in);

    private static User_View uv = new User_View();

    private static User_Controller uc = new User_Controller();

    public void run() throws SQLException {
        System.out.println("欢迎光临滴答");
        int i;
        do {
            System.out.println("【新用户注册请输入1】\t【老用户登录请输入2】");
            i = in.nextInt();
            if (i == 1) {
                uc.register();
                uv.menu();
            }
            if (i == 2) {
                do {
                    System.out.println("请输入您的昵称或手机号：\t");
                    String name = in.next();
                    if (name.equals("exit")) {
                        Controller.exit();
                    }
                    System.out.println("请输入您的密码: \t");
                    String password = in.next();
                    User_Controller uc = new User_Controller();
                    if (name.length() == 11 && Util.isAllDigit(name)) {
                        if (uc.login(Long.parseLong(name), password)) {
                            uv.menu();
                        }
                    } else {
                        if (uc.login(name, password)) {
                            uv.menu();
                        }
                    }
                    System.out.println("输入的帐号密码有误，请重新输入，退出请输入exit");
                } while (true);
            }
        } while (i != 1 && i != 2);
    }

    /**
     * 用户功能菜单
     *
     * @throws SQLException
     */
    public void menu() throws SQLException {
        final String content = "主菜单：\n"
                + "[o/order]\t订车\n"
                + "[s/search]\t查询历史订单\n"
                + "[e/edit]\t修改账户信息\n"
                + "[b/back]\t返回上一级\n"
                + "[q/quit]\t退出系统";
        while (true) {
            System.out.println(content);
            switch (in.next().substring(0, 1).toUpperCase()) {
                case "O":
                    uc.order();
                    break;
                case "S":
                    uc.queryHistoryOrderList();
                    break;
                case "E":
                    uc.editAccountInfo();
                    break;
                case "B":
                    uv.run();
                    break;
                case "Q":
                    Controller.exit();
            }
        }
    }
}
