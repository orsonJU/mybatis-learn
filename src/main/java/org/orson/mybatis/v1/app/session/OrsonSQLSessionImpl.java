package org.orson.mybatis.v1.app.session;

import org.orson.mybatis.v1.app.configuration.OrsonConfiguration;
import org.orson.mybatis.v1.app.proxy.OrsonMapperProxy;

import java.lang.reflect.Proxy;


/**
 * Created by orson on 2018/9/24.
 */
public class OrsonSQLSessionImpl implements OrsonSQLSession {


    private OrsonConfiguration configuration;


    public OrsonSQLSessionImpl(OrsonConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new OrsonMapperProxy(this.configuration));

    }
}
