package com.night.utils;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * 工具类
 * @author Liu
 * @// TODO: 2017/3/31  generateOrderNumber()方法需添加同步锁
 */
public class Util {

    private static String[] chars = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * @return 例如“hL00XeHN”这样8位由26个小写、26个大写和0-9数字随机组成的字符串
     */
    public static String generateUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     *订单格式：年+月+日+该日订单号,12位，例：201702151234
     */
    public static String generateOrderNumber() throws Exception {

        /**
         * 从com.night.conf.Constant.properties下读取每日订车次数
         */
        Properties properties = new Properties();
        try {
            properties.load(Util.class.getResourceAsStream("/com/night/conf/Constant.properties"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        String times = properties.getProperty("day_order_times");
        if (Integer.parseInt(times) > 19999) {
            throw new Exception("本日订单已满，请明日惠顾，谢谢");
        }
        StringBuffer str = new StringBuffer();
        Formatter fm = new Formatter();
        str.append(fm.format("%1$tY%1$tm%1$td", Calendar.getInstance())).append(String.valueOf(times).substring(1, 3));
        times = String.valueOf(Integer.parseInt(times)+1);
        properties.setProperty("day_order_times",times);
        properties.store(new FileOutputStream(Util.class.getResource("/com/night/conf/Constant.properties").getFile()),"");
        return str.toString();
    }

    /*
     *设置每日零时更新Constant.properties文件
     */
    private static void updateConf(){
        Calendar cal = Calendar.getInstance();
        Timer timer = new Timer();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date date = new Date(cal.getTimeInMillis());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Properties properties = new Properties();
                try {
                    properties.load(Util.class.getResourceAsStream("/com/night/conf/Constant.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //更新每日订单数
                properties.setProperty("day_order_times","10000");
                try {
                    properties.store(new FileOutputStream(Util.class.getResource("/com/night/conf/Constant.properties").getFile()),"update day_order_times");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },date,1000*60*60*24);
    }

    public static boolean isAllDigit(String str){
        char[] ch = str.toCharArray();
        boolean bool = true;
        for (Character c:ch) {
            if (!Character.isDigit(c)){
                bool = false;
                break;
            }
        }
        return bool;
    }
}
