package org.subbs.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName UpdateEntity
 * @Date 2020/1/4 22:17
 */
public class EntityUtil {

    /**
     * 将newObj 中非空的属性，更新到 oriObj中
     */
    public static Object UpdateEntity(Object oriObj, Object newObj) {
        Field fields[] = newObj.getClass().getDeclaredFields();
        Class clazz = oriObj.getClass();

        try {
            for (Field field : fields) {
                String keyName = field.getName();

                String methodName = keyName.substring(0, 1).toUpperCase() + keyName.substring(1);
                String getMethodName = "get" + methodName;
                String setMethodName = "set" + methodName;
                Method getter = clazz.getDeclaredMethod(getMethodName);
                Method setter = clazz.getDeclaredMethod(setMethodName,field.getType());
                Object value = getter.invoke(newObj);
                if (value != null) {
                    setter.invoke(oriObj, value);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return oriObj;
    }
}
