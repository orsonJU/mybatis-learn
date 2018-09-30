package org.orson.mybatis.v1.app;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by orson on 2018/9/24.
 */
public class MyBatisTest {


    public  static void main(String[] args) throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        //List<Object> object = session.selectList("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", new Object[]{"Orson"});

        Object object = session.selectOne("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", "Orson");

        System.out.println(object);

    }
}
