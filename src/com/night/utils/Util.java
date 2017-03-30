package com.night.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.UUID;

/**
 * 工具类
 * @author Liu
 */
public class Util {
	
	private static int Auto_id = 1;

    private static Connection rootconn;

    private static String sql;

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

    private static int serial_number = 100;

    /**
     *订单格式：年+月+日+该日订单号,11位，例：2017021511
     */
    public static int generateOrderNumber() throws Exception {
        if (serial_number > 199) {
            throw new Exception("本日订单已满，请明日惠顾，谢谢");
        }
        Calendar cal = Calendar.getInstance();
        StringBuffer str = new StringBuffer();
        Formatter fm = new Formatter();
        if (cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 0) {
            serial_number = 100;
        }
        str.append(fm.format("%1$tY%1$tm%1$td", cal)).append(String.valueOf(serial_number).substring(1, 3));
        serial_number++;
        return Integer.valueOf(str.toString());
    }

    /**
     *返回字符串形式：“1|1|1|1|1|1”
     */
    public static String getString(ArrayList<Integer> list) {
        StringBuffer str = new StringBuffer();
        if (list.size() == 0) {
            str.append("订单为空");
        } else {
            for (int i = 0; i < list.size(); i++) {
                str.append(list.get(i)).append("|");
            }
            str.deleteCharAt(str.length());
        }
        return str.toString();
    }
    
    /**
     * 将{@link Util#getString(ArrayList)}生成的“1|1|1|1”形式字符串转换为{@link ArrayList}
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> getList(String str){
        ArrayList<Integer> array = new ArrayList<>();
        String[] a = str.split("\\|");
        for (String s:a) {
            array.add(Integer.valueOf(s));
        }
        return array;
    }
    
    public static int generateAutoId(){
    	Auto_id++;
    	return Auto_id;
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
