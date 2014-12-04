package com.huanwuji.lps.utils.lang;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * description:.
 * User: huanwuji
 * create: 13-7-28 下午10:23
 */
public class ObjectTools {

    public static Field getField(Class type, String fieldName) {
        if (fieldName.contains(StringTools.PERIOD)) {
            String[] fieldNames = fieldName.split("\\.");
            Class swapClass = type;
            for (int i = 0; i < fieldNames.length - 1; i++) {
                String name = fieldNames[i];
                Field field = getDeepField(swapClass, name);
                swapClass = field.getType();
            }
            return getDeepField(swapClass, fieldNames[fieldNames.length - 1]);
        } else {
            return getDeepField(type, fieldName);
        }
    }

    public static Field getDeepField(Class type, String fieldName) {
        Field field = FieldUtils.getDeclaredField(type, fieldName, true);
        if (field == null) {
            field = getSupperClassField(type, fieldName);
        }
        return field;
    }

    public static Field getSupperClassField(Class type, String fieldName) {
        List<Class> supperClasses = ClassUtils.getAllSuperclasses(type);
        Field field = null;
        for (Class supperClass : supperClasses) {
            field = FieldUtils.getDeclaredField(supperClass, fieldName, true);
            if (field != null) {
                break;
            }
        }
        return field;
    }
}
