package org.orson.mybatis.v1.app;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.orson.mybatis.v1.app.mapper.PersonMapper;

import java.io.InputStream;
import java.util.List;

/**
 * Created by orson on 2018/9/24.
 */
public class MyBatisTest {


    public  static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession session = sqlSessionFactory.openSession();

        PersonMapper mapper = session.getMapper(PersonMapper.class);

        List<Person> person = mapper.findPersonByName("Orson");

        System.out.println(person);


    }
}
