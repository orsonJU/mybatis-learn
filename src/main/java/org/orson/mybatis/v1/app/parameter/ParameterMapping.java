package org.orson.mybatis.v1.app.parameter;

import org.orson.mybatis.v1.app.enums.JDBCType;

/**
 * Created by orson on 2018/9/30.
 */
public class ParameterMapping {

    private int index;

    private String name;

    private Class<?> javaType;

    private JDBCType jdbcType;

    public ParameterMapping(int index, String name, Class<?> javaType, JDBCType jdbcType) {
        this.index = index;
        this.name = name;
        this.javaType = javaType;
        this.jdbcType = jdbcType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }

    public JDBCType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JDBCType jdbcType) {
        this.jdbcType = jdbcType;
    }
}
