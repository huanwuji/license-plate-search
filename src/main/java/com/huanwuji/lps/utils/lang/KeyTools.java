package com.huanwuji.lps.utils.lang;

/**
 * description:.
 * User: huanwuji
 * create: 13-7-7 下午1:19
 */
public class KeyTools {

    public static long getFk(String key, long id) {
        assert id <= Integer.MAX_VALUE && id >= Integer.MIN_VALUE;
        return id << 32 | key.hashCode();
    }
}
