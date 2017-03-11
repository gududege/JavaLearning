package com.utils.xml;

import java.util.Scanner;

public class ParseFactory {

    public static ParseXml newInstance(String ParseType) {
        ParseXml instance = null;
        switch (ParseType) {
            case "dom":
                instance = ParseXmlBydom.newInstance();
                break;
            case "dom4j":
                instance = ParseXmlBydom4j.newInstance();
                break;
        }
        if (!ParseType.equals("dom")  && !ParseType.equals("dom4j")){
            System.out.println("请输入dom, dom4j其中一种方式");
            Scanner in = new Scanner(System.in);
            instance = newInstance(in.next());
        }
            return instance;
    }
}
