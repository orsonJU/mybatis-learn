package org.orson.mybatis.v1.app.mapper;

import org.apache.ibatis.annotations.Param;
import org.orson.mybatis.v1.app.Person;

import java.util.List;

/**
 * Created by orson on 2018/9/24.
 */
public interface PersonMapper {

    public List<Person> findPersonByName(@Param("name") String name);

    public List<Person> findPersonByName(@Param("name") String name, @Param("age") Integer age);

    public List<Person> findPersonByAge(@Param("age") Integer age);
}
