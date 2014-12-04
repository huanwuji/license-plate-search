package com.huanwuji.lps.utils.lang;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * <p/>
 * User: juyee
 * Date: 12-9-7
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */
public class CollectionTools {

    public static <T> Set<T> listToSet(List list, String properName, Class<T> clazz) {
        Set<T> set = new HashSet<T>();
        if (list == null || list.size() == 0) {
            return set;
        }

        try {
            for (Object o : list) {
                Object value = PropertyUtils.getProperty(o, properName);
                if (value != null) {
                    set.add((T) value);
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return set;
    }
}
