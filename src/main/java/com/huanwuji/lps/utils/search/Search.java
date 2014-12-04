package com.huanwuji.lps.utils.search;

import java.lang.annotation.ElementType;

/**
 * description:.
 * User: huanwuji
 * create: 13-7-27 上午11:21
 */
@java.lang.annotation.Target({ElementType.METHOD,
        ElementType.TYPE, ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Search {
    boolean search() default true;

    boolean sort() default true;

    boolean all() default false;
}
