package com.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class WriteXmlBydom {
	
	private static final String rootElementname = "AutoInfo";
	
	public static WriteXmlBydom newInstance() {
		return new WriteXmlBydom();
	}
	
	private WriteXmlBydom() {
	}
	
	public <E> void write(File xmlfile, ArrayList<E> sourceList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		
		//创建dom树
		Document document = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
		} catch (ParserConfigurationException e) {
			System.out.println("parse xmlfile error with ParserConfigurationException");
		}
		Node rootelement = document.createElement(rootElementname);
		for (E e : sourceList) {
			Node childNode = document.createElement(e.getClass().getSimpleName());
			Field[] objectattr = e.getClass().getFields();
			for (Field attr : objectattr) {
				String attrname = attr.getName();
				String firstLetter = attrname.substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + attrname.substring(1);
				Method gettermethod = e.getClass().getMethod(getter);
				String attrvalue = String.valueOf(gettermethod.invoke(e)) ;
				Node attrnode = document.createElement(attrname);
				attrnode.setTextContent(attrvalue);
				childNode.appendChild(attrnode);
			}
			rootelement.appendChild(childNode);
		}
		
		//转换dom树
		StreamResult streamResult = new StreamResult(xmlfile);
		DOMSource domSource = new DOMSource(rootelement);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			//设置首行缩进、且缩进4个字符
			transformer.setOutputProperty("indent", "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(domSource, streamResult);
		} catch (
				TransformerException e)	{
			e.printStackTrace();
		}
	}
}
