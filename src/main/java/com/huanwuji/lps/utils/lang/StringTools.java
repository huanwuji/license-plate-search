package com.huanwuji.lps.utils.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description:.
 * User: huanwuji
 * create: 13-6-14 下午10:20
 */
public class StringTools {
    public static final String COMMA = ",";
    public static final String MINUS = "-";
    public static final String PERIOD = ".";

    public static List<String> getMatchs(String str, String reg) {
        return getMatchs(str, reg, -1);
    }

    public static List<String> getMatchs(String str, String reg, int group) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        List<String> matchs = new ArrayList<String>();
        while (matcher.find()) {
            if (group == -1) {
                matchs.add(matcher.group());
            } else {
                matchs.add(matcher.group(group));
            }
        }
        return matchs;
    }

    public static String toJavaName(String str, boolean isFistLetterUp) {
        StringBuilder sb = new StringBuilder();
        String ucStr = str.toLowerCase();
        if (ucStr != null && ucStr.length() != 0) {
            if (isFistLetterUp) {
                sb.append(Character.toUpperCase(ucStr.charAt(0)));
            } else {
                sb.append(ucStr.charAt(0));
            }
            for (int i = 1; i < ucStr.length(); i++) {
                char c = ucStr.charAt(i);
                if (c == '_') {
                    if ((++i) < ucStr.length()) {
                        sb.append(Character.toUpperCase(ucStr.charAt(i)));
                    }
                } else {
                    sb.append(ucStr.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    public static String toJsonP(String callback, String json) {
        StringBuilder sb = new StringBuilder();
        return sb.append(callback).append("(").append(json).append(")").toString();
    }
}
