package org.orson.mybatis.v1.app.enums;

/**
 * Created by orson on 2018/9/30.
 */
public enum JDBCType {

    VARCHAR("varchar"), INT("int");

    private String name;

    JDBCType(String name) {
        this.name = name;
    }
}
