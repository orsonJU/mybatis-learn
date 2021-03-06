package org.orson.mybatis.v1.app.session;

import org.orson.mybatis.v1.app.Person;
import org.orson.mybatis.v1.app.configuration.Configuration;
import org.orson.mybatis.v1.app.configuration.MappedStatement;
import org.orson.mybatis.v1.app.enums.StatementType;
import org.orson.mybatis.v1.app.parameter.ParameterMapping;
import org.orson.mybatis.v1.app.proxy.MapperProxy;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by orson on 2018/9/24.
 */
public class SqlSessionImpl implements SqlSession {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/exe?useSSL=false";


    static final String USER = "root";
    static final String PASS = "root";


    private Configuration configuration;


    public SqlSessionImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(this.configuration));
    }

    @Override
    public <T> T selectOne(String statementId) {
        return (T)this.selectOne(statementId, null);
    }

    @Override
    public <T> T selectOne(String statementId, Object[] arguments) {
        List<T> result = this.selectList(statementId, arguments);
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object[] arguments) {
        MappedStatement boundStatement = configuration.getBoundStatement(statementId);

        StatementType statementType = boundStatement.getStatementType();

        switch (statementType) {
            case SELECT:
                break;
            case DELETE:
                break;
            case UPDATE:
             break;
            case INSERT:
                break;
            default:
        }


        List personList = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);

            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String boundSql = boundStatement.getSql();

            //resolve parameter

            PreparedStatement pstat = conn.prepareStatement(boundSql);

            List<ParameterMapping> parameterMappings = boundStatement.getParameterMappings();

            for(ParameterMapping parameterMapping : parameterMappings) {

                Class<?> javaType = parameterMapping.getJavaType();

                if(javaType.isAssignableFrom(String.class)) {
                    pstat.setString(parameterMapping.getIndex(), String.valueOf(arguments[parameterMapping.getIndex() - 1]));
                }else if(javaType.isAssignableFrom(Integer.class)) {
                    pstat.setInt(parameterMapping.getIndex(), Integer.valueOf(String.valueOf(arguments[parameterMapping.getIndex() - 1])));
                }

            }

            ResultSet rs = pstat.executeQuery();


            while(rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                personList.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }
}
