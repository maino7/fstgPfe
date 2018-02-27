/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author moulaYounes
 */
public class DaoEngine {
    //************************************************

    private static final List<Class> types = new ArrayList<>();

    static {
        types.add(Boolean.class);
    }
    //**********************************************

    public static void launchSetter(Object objet, Method methodSetter, Object value) throws Exception {
        Object[] objects = new Object[1];
        objects[0] = value;
        methodSetter.invoke(objet, objects);
    }

    // Abed's Modifications
    public static void launchSetterByName(Object objet, String setter, String value) throws Exception {
        List<Method> setters = getSetterList(objet.getClass());
        for (int i = 0; i < setters.size(); i++) {
            Method method = setters.get(i);
            if (method.getName().toLowerCase().equals(("set" + setter).toLowerCase())) {
                String c = method.getParameterTypes()[0].getTypeName();
                Object setterValue = convertType(Class.forName(c), value);
                launchSetter(objet, method, setterValue);
            }
        }
    }

    // Abed's Modifications
    public static Object convertType(Class setterType, String value) {
        if (Boolean.class == setterType || Boolean.TYPE == setterType) {
            return Boolean.parseBoolean(value);
        }
        if (Byte.class == setterType || Byte.TYPE == setterType) {
            return Byte.parseByte(value);
        }
        if (Short.class == setterType || Short.TYPE == setterType) {
            return Short.parseShort(value);
        }
        if (Integer.class == setterType || Integer.TYPE == setterType) {
            return Integer.parseInt(value);
        }
        if (Long.class == setterType || Long.TYPE == setterType) {
            return Long.parseLong(value);
        }
        if (Float.class == setterType || Float.TYPE == setterType) {
            return Float.parseFloat(value);
        }
        if (Double.class == setterType || Double.TYPE == setterType) {
            return Double.parseDouble(value);
        }
        if (Date.class == setterType) {
            return DateUtil.convertForDaoLauncher(value);
        }
        return value;
    }

    public static List<Method> getSetterList(Class myClass) throws Exception {
        Method[] methods = myClass.getMethods();
        List<Method> getters = new ArrayList<>();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("set") && !method.getParameterTypes()[0].getName().equals("java.util.List")) {
                getters.add(method);
            }
        }

        return getters;
    }

}
