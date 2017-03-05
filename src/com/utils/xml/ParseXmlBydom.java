package com.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * dom方式解析xml
 */
public class ParseXmlBydom extends ParseXml {
	
	public static ParseXmlBydom newInstance() {
		return new ParseXmlBydom();
	}
	
	private ParseXmlBydom() {
	}
	
	/**
	 * dom方式解析简单三层xml树，返回document对象
	 */
	public static Document getDocument(File xml) {
		Document document = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(xml);
		} catch (SAXException e) {
			System.out.println("parse xmlfile error with SAXException");
		} catch (ParserConfigurationException e) {
			System.out.println("parse xmlfile error with ParserConfigurationException");
		} catch (IOException e) {
			System.out.println("parse xmlfile error with IOException");
		}
		return document;
	}
	
	public <E> void read(File xmlfile, ArrayList<E> returnList, Class<E> eClass)
			throws IllegalAccessException, InstantiationException {
		Document document = getDocument(xmlfile);
		Element root = document.getDocumentElement();
		NodeList autolist = root.getChildNodes();
		for (int i = 0; i < autolist.getLength(); i++) {
			Node autochildnode = autolist.item(i);
			if (autochildnode.getNodeType() != Node.TEXT_NODE) {
				E e = eClass.newInstance();
				NodeList attrinodelist = autochildnode.getChildNodes();
				for (int j = 0; j < attrinodelist.getLength(); j++) {
					Node attrnode = attrinodelist.item(j);
					if (attrnode.getNodeType() != Node.TEXT_NODE) {
						addObjectAttr(e, attrnode.getNodeName(),
								attrnode.getTextContent());
					}
				}
				returnList.add(e);
				e = null;
			}
		}
	}
}
