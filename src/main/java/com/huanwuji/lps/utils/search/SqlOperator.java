package com.huanwuji.lps.utils.search;

/**
 * description:.
 * User: huanwuji
 * create: 13-7-27 上午11:28
 */
public enum SqlOperator {
    EQ("="),
    LT("<"),
    LTE("<="),
    LIKE("LIKE"),
    GT(">"),
    GTE(">="),
    NULL("IS NULL"),
    NOT_NULL("IS NOT NULL"),
    DESC("DESC"),
    ASC("ASC");

    private String operator;

    private SqlOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
