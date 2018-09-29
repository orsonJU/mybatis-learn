package org.orson.mybatis.v1.app.session;

import org.orson.mybatis.v1.app.configuration.Configuration;
import org.orson.mybatis.v1.app.proxy.MapperProxy;

import java.lang.reflect.Proxy;


/**
 * Created by orson on 2018/9/24.
 */
public class SqlSessionImpl implements SqlSession {


    private Configuration configuration;


    public SqlSessionImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(this.configuration));

    }
}
