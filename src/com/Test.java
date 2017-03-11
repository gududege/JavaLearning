package com;

import com.google.gson.Gson;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.model.User;
import com.model.User_Table;

import java.sql.Connection;


public class Test {

    public static void main(String[] args) throws Exception {
        User_Table ut = new User_Table();
        User user = ut.query("1=1 limit 1","","");
        System.out.println(user.toString());
    }
}
