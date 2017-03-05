package com.utils.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * dom4j方式解析xml
 */
public class ParseXmlBydom4j extends ParseXml {
	
	static ParseXmlBydom4j newInstance() {
		return new ParseXmlBydom4j();
	}
	
	private ParseXmlBydom4j() {
	}
	
	public static Document getDocument(File xmlfile) {
		SAXReader sr = new SAXReader();
		Document document = null;
		try {
			document = sr.read(xmlfile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	@Override
	public <E> void read(File xml, ArrayList<E> returnList, Class<E> eClass) throws IllegalAccessException,
			InstantiationException {
		Document document = getDocument(xml);
		Element rootElement = document.getRootElement();
		Iterator<Element> rootiterator = rootElement.elementIterator();
		while (rootiterator.hasNext()) {
			E object = eClass.newInstance();
			Element autoelement = rootiterator.next();
			Iterator<Element> autoiterator = autoelement.elementIterator();
			while (autoiterator.hasNext()) {
				Element childnode = autoiterator.next();
				addObjectAttr(object, childnode.getName(), childnode.getText());
			}
			returnList.add(object);
			object = null;
		}
	}
}
