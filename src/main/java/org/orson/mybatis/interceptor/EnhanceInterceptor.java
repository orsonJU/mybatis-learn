package org.orson.mybatis.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * Created by orson on 2018/9/23.
 */
public abstract class EnhanceInterceptor implements Interceptor {


    private Properties properties = null;

    /**
     * before interceptor
     */
    public abstract void beforeIntercept();

    /**
     * post interceptor
     * @param result
     */
    protected abstract void postIntercept(Object result);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        this.beforeIntercept();

        Object result = invocation.proceed();

        this.postIntercept(result);

        return result;
    }



    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    public <T> T getValue(String name) {
        return (T) properties.get(name);
    }
}
