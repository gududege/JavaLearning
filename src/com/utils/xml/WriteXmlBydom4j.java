package com.utils.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class WriteXmlBydom4j {
	
	public static WriteXmlBydom4j newInstance() {
		return new WriteXmlBydom4j();
	}
	
	private WriteXmlBydom4j() {
	}
	
	public <E> void write(File xmlfile, ArrayList<E> sourceList) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException, IOException {
		
		//创建dom4j树
		Document document = DocumentHelper.createDocument();
		Element rootelement = document.addElement("AutoInfo");
		for (E e : sourceList) {
			Element childNode = rootelement.addElement(e.getClass().getSimpleName());
			Field[] objectattr = e.getClass().getFields();
			for (Field attr : objectattr) {
				String attrname = attr.getName();
				String firstLetter = attrname.substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + attrname.substring(1);
				Method gettermethod = e.getClass().getMethod(getter);
				String attrvalue = String.valueOf(gettermethod.invoke(e)) ;
				Element attrnode = childNode.addElement(attrname);
				attrnode.setText(attrvalue);
			}
		}
		
		//转换dom4j树
		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfile), outputFormat);
		writer.write(document);
		writer.close();
	}
}

