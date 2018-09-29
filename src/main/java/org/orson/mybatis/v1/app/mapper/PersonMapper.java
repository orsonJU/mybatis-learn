package org.orson.mybatis.v1.app.mapper;

import org.orson.mybatis.v1.app.Person;

import java.util.List;

/**
 * Created by orson on 2018/9/24.
 */
public interface PersonMapper {

    public List<Person> findPersonByName(String name);
}
