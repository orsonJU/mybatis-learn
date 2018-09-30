package org.orson.mybatis.v1.app.configuration;


import org.orson.mybatis.v1.app.enums.ResultType;
import org.orson.mybatis.v1.app.enums.StatementType;
import org.orson.mybatis.v1.app.parameter.ParameterMapping;

import java.util.List;

/**
 * represent the a statement in mapper.xml file
 * Created by orson on 2018/9/30.
 */
public class MappedStatement {

    /**
     * the mapper element id
     */
    private String id;


    /**
     * the statement type, select/insert/update/delete
     */
    private StatementType statementType;


    /**
     * the result type, type/map
     */
    private ResultType resultType;


    /**
     * parameter mapping
     */
    private List<ParameterMapping> parameterMappings;


    /**
     * sql statement source
     */
    private String sql;


    public MappedStatement(String id, StatementType statementType, ResultType resultType, List<ParameterMapping> parameterMappings, String sql) {
        this.id = id;
        this.statementType = statementType;
        this.resultType = resultType;
        this.parameterMappings = parameterMappings;
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public void setStatementType(StatementType statementType) {
        this.statementType = statementType;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

