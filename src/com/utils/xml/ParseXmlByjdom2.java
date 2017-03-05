package com.utils.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * jdom2方式解析xml
 */
public class ParseXmlByjdom2 extends ParseXml {

    static ParseXmlByjdom2 newInstance() {
        return new ParseXmlByjdom2();
    }

    private ParseXmlByjdom2() {
    }

    public static Document getDocument(File xmlfile) {
        SAXBuilder sax = new SAXBuilder();
        Document document = null;
        try {
            document = sax.build(xmlfile);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    
    @Override
    public <E> void read(File xmlfile, ArrayList<E> returnList, Class<E> eClass) throws Exception {
        Document document = getDocument(xmlfile);
        Element rootelemnet = document.getRootElement();
        List<Element> rootlist = rootelemnet.getChildren();
        for (Element ele : rootlist) {
            E object = eClass.newInstance();
            List<Element> childele = ele.getChildren();
            for (Element child : childele) {
                addObjectAttr(object, child.getName(), child.getText());
            }
            returnList.add(object);
            object = null;
        }
    }
}
