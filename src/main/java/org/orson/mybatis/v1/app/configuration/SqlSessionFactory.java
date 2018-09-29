package org.orson.mybatis.v1.app.configuration;

/**
 * Created by orson on 2018/9/26.
 */
public class SqlSessionFactory {

    Configuration configuration;


    public SqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
