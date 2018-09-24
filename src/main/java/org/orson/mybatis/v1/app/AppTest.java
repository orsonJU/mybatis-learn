package org.orson.mybatis.v1.app;

import org.orson.mybatis.v1.app.configuration.OrsonConfiguration;
import org.orson.mybatis.v1.app.session.OrsonSQLSessionImpl;

import java.util.List;

/**
 * Created by orson on 2018/9/24.
 */
public class AppTest {

    public static void main(String[] args) throws Exception {


        OrsonConfiguration conf = new OrsonConfiguration();

        OrsonSQLSessionImpl sqlSession = new OrsonSQLSessionImpl(conf);


        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);


        List<Person> list = personMapper.findPersonByName("Marting");

        System.out.println(list);
    }
}
