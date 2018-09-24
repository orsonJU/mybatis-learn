package org.orson.mybatis.v1.app.proxy;

import org.orson.mybatis.v1.app.Person;
import org.orson.mybatis.v1.app.configuration.OrsonConfiguration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by orson on 2018/9/24.
 */
public class OrsonMapperProxy implements InvocationHandler {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/exe?useSSL=false";


    static final String USER = "root";
    static final String PASS = "root";

    private OrsonConfiguration configuration;

    public OrsonMapperProxy(OrsonConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        //get method reference and its sql mapping
        String boundSQL = this.configuration.getBoundSQL(method.getDeclaringClass()+ "."+method.getName());

        Class.forName(JDBC_DRIVER);

        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

        PreparedStatement pstat = conn.prepareStatement(boundSQL);

        pstat.setString(1, (String) args[0]);

        ResultSet rs = pstat.executeQuery();

        List<Person> personList = new ArrayList<>();
        while(rs.next()) {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
            personList.add(person);
        }


        return personList;
    }
}
