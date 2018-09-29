package org.orson.mybatis.v1.app.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by orson on 2018/9/24.
 */
public class Configuration {



    /**
     * Mybatis support customized properties, a property replace a placeholder - ${property name}
     */
    private Properties properties = new Properties();


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


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
