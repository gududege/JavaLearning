package com.utils.xml;

import java.io.File;
import java.io.IOException;

public class WriteXmlByjdom2 {

    public static WriteXmlByjdom2 newInstance(){
        return new WriteXmlByjdom2();
    }

    private WriteXmlByjdom2() {
    }

    public void write(String xmlpath){
    
        //创建xml文件
        File xmlfile = new File(xmlpath);
        if (!xmlfile.exists()) {
            try {
                xmlfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //创建jdom2树
    }
}
