package org.orson.mybatis.v1.app;

import org.orson.mybatis.v1.app.builder.SqlSessionFactoryBuilder;
import org.orson.mybatis.v1.app.builder.parser.support.dom4j.Dom4jConfigurationParser;
import org.orson.mybatis.v1.app.configuration.SqlSessionFactory;
import org.orson.mybatis.v1.app.session.SqlSession;


/**
 * Created by orson on 2018/9/24.
 */
public class AppTest {

    public static void main(String[] args) throws Exception {


        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(new Dom4jConfigurationParser());
        SqlSessionFactory sf = builder.build(AppTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));

        SqlSession session = sf.openSession();

        Object person = session.selectOne("org.orson.mybatis.v1.app.mapper.PersonMapper.findPersonByName", new Object[]{"Orson"});

        System.out.println(person);
    }
}
