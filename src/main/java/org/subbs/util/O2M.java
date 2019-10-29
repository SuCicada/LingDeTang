package org.subbs.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/24/19
 * Time: 12:09 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class O2M {
    public static Map parse(Object object){
        return parse(object, object.getClass().getDeclaredFields());
    }

    public static Map parse(Object object,String ... name){
        Field fields[] = new Field[name.length];
        Class clazz = object.getClass();
        try {
            for(int i=0;i<name.length;i++){
                fields[i] = clazz.getDeclaredField(name[i]);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return parse(object,fields);
    }

    public static Map parse(Object object,Field ... fields){
        Map res = new HashMap();
        Class clazz = object.getClass();
        try {
            for(Field field: fields){
                String keyName = field.getName();
                String methodName = "get"+keyName.substring(0,1).toUpperCase()+keyName.substring(1);
                Method getter = clazz.getDeclaredMethod(methodName);
                Object value = getter.invoke(object);
                res.put(keyName,value);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }
}
