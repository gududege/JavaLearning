package com;

import com.model.User_Table;

import java.util.AbstractList;

public class test {

    private static int i = 23;

    public static void main(String[] args) throws Exception {
//        Auto_Table at = new Auto_Table();
//        at.createTable();
////        Auto auto = new Auto("帝豪","car",10,1000,5,200);
//////        at.add(auto);
////        System.out.println( auto.toString());
////        at.update(auto);
//        Auto a = new Auto();
//        System.out.println(a.toString());
//        Map<String,String> m = new HashMap<>();
//        m.put("condition","id");
//        m.put("relation","<");
//        m.put("value","1");
//        a = at.query(m);
//        System.out.println(a.toString());
//        User_Controller oa =new User_Controller();
//        oa.printALLAutoInfo();
        User_Table ut = new User_Table();
        AbstractList array = ut.getObjects("true", "", "");

        System.out.println(array.size());
        System.out.println(array.get(0).toString());


    }
}
