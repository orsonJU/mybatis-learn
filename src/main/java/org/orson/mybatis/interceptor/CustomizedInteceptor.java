package org.orson.mybatis.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * Created by orson on 2018/9/23.
 */
public class CustomizedInteceptor implements Interceptor {


    /**
     * Here is a standard proxy invocation
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //before method invoke

        Object result = invocation.proceed();

        //after method invoke

        return result;
    }

    /**
     * mybatis provides a static method Plugin.wrap to return a proxy object
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     *
     * Mybatis provides set properties method to get properties from xml configuration
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        //properties from xml
    }
}
