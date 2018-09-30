package org.orson.mybatis.v1.app.enums;

/**
 * Created by orson on 2018/9/30.
 */
public enum StatementType {

    SELECT("select"), INSERT("insert"), UPDATE("update"), DELETE("delete");

    private String name;

    StatementType(String name) {
        this.name = name;
    }


    public String value() {
        return this.name;
    }
}
