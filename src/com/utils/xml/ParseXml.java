package com.utils.xml;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class ParseXml {

    static void addObjectAttr(Object object, String attrname, String attritvalue) {

        String firstLetter = attrname.substring(0, 1).toUpperCase();
        String setter = "set" + firstLetter + attrname.substring(1);

        try {
            Field field = object.getClass().getDeclaredField(attrname);
            switch (field.getType().toString()) {
                case "int":
                    Method setterInt = object.getClass().getMethod(setter, int.class);
                    setterInt.invoke(object, Integer.valueOf(attritvalue));
                    break;
                case "double":
                    Method setterDouble = object.getClass().getMethod(setter, double.class);
                    setterDouble.invoke(object, Double.valueOf(attritvalue));
                    break;
                case "long":
                    Method setterLong = object.getClass().getMethod(setter, Long.class);
                    setterLong.invoke(object, Long.valueOf(attritvalue));
                    break;
                case "float":
                    Method setterFloat = object.getClass().getMethod(setter, Float.class);
                    setterFloat.invoke(object, Float.valueOf(attritvalue));
                    break;
                case "class java.lang.String":
                    Method setterString = object.getClass().getMethod(setter, String.class);
                    setterString.invoke(object, String.valueOf(attritvalue));
                    break;
            }
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    public abstract <E> void read(File xml, ArrayList<E> returnList, Class<E> eClass) throws Exception;
}


