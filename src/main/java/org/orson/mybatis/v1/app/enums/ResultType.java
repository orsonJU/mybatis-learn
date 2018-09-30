package org.orson.mybatis.v1.app.enums;

/**
 * Created by orson on 2018/9/30.
 */
public enum ResultType {

    RESULT_MAP("resultMap"), RESULT_TYPE("resultType");

    private String name;

    ResultType(String name) {
        this.name = name;
    }
}
