package org.orson.mybatis.v1.app.configuration;

import org.orson.mybatis.v1.app.session.SqlSession;
import org.orson.mybatis.v1.app.session.SqlSessionImpl;

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


    /**
     * create a default sql session.
     * @return
     */
    public SqlSession openSession() {
        return new SqlSessionImpl(this.configuration);
    }
}
