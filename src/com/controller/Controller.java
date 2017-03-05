package com.controller;

import com.model.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    public void printALLInfo(Table table) throws SQLException {
        ArrayList arrays = table.getObjects("true"," "," ");
        int i = 1;
        for (Object a: arrays) {
            System.out.println("["+(i++)+"]\t"+a.toString());
        }
    }

    /**
     * 退出系统
     */
    public static void exit(){
        System.exit(0);
    }
}


