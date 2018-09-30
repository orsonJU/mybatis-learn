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


    /**
     * mapper statement registry
     */
    private Map<String, MappedStatement> mappedStatementRegistry = new HashMap<>();


    private static final Map<String, String> sqlMapping = new HashMap<>();


    static {
        sqlMapping.put("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", "select id, name, age from person where name = ?");
    }


    //public Configuration() {
    //
    //    List<ParameterMapping> mapping = new ArrayList<>();
    //    mapping.add(new ParameterMapping(1, "name", String.class, JDBCType.VARCHAR));
    //    //mapping.add(new ParameterMapping(2, "age", Integer.class, JDBCType.INT));
    //
    //    MappedStatement mappedStatement = new MappedStatement("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", StatementType.SELECT, ResultType.RESULT_TYPE, mapping, "select id, name, age from person where name = ?");
    //
    //
    //    List<ParameterMapping> mapping2 = new ArrayList<>();
    //    mapping2.add(new ParameterMapping(1, "age", Integer.class, JDBCType.INT));
    //    MappedStatement mappedStatement2 =new MappedStatement("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByAge", StatementType.SELECT, ResultType.RESULT_TYPE, mapping2, "select id, name, age from person where age = ?");
    //
    //    mappedStatementRegistry.put("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", mappedStatement);
    //    mappedStatementRegistry.put("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByAge", mappedStatement2);
    //}

    /**
     * get target SQL content according to the bound method name
     * @param methodName
     * @return
     */
    public String getBoundSQL(String methodName) {
        return sqlMapping.get(methodName);
    }


    /**
     * get mapped statement by statement id
     * @param statementId
     * @return
     */
    public MappedStatement getBoundStatement(String statementId) {
        return this.mappedStatementRegistry.get(statementId);
    }


    /**
     * save mapped statement
     * @param statement
     */
    public void addMappedStatement(MappedStatement statement) {
        this.mappedStatementRegistry.put(statement.getId(), statement);
    }


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
