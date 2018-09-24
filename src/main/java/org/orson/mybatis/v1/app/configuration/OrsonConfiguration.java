package org.orson.mybatis.v1.app.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by orson on 2018/9/24.
 */
public class OrsonConfiguration {


    private static final Map<String, String> sqlMapping = new HashMap<>();


    static {
        sqlMapping.put("interface org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", "select id, name, age from person where name like ?");

    }


    /**
     * get target SQL content according to the bound method name
     * @param methodName
     * @return
     */
    public String getBoundSQL(String methodName) {
        return sqlMapping.get(methodName);
    }
}
