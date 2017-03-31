package com.night.utils;

import com.night.dao.UserMapper;
import com.night.dao.dbDao;
import com.night.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static void main(String[] args) {


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Calendar cal = Calendar.getInstance();
                    Properties properties = new Properties();
                    try {
                        properties.load(new FileInputStream("Constant.properties"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(properties.getProperty("day_order_times"));
                    //更新每日订单数
                    properties.setProperty("day_order_times", cal.toString());
                    try {
                        properties.store(new FileOutputStream("Constant.properties"), "update day_order_time");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 1000, 3000);
        while (true) {
            try {
                if (System.in.read()=='s'){
                    timer.cancel();
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
