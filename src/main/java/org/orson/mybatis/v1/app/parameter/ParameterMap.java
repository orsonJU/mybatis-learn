package org.orson.mybatis.v1.app.parameter;


import java.util.List;

/**
 * Created by orson on 2018/9/30.
 */
public class ParameterMap {


    /**
     * represent the method and the sql mapping
     */
    private List<ParameterMapping> parameterMappings;


    public ParameterMap(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
