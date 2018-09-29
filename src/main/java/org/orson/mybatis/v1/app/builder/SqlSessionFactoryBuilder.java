package org.orson.mybatis.v1.app.builder;

import org.orson.mybatis.v1.app.builder.parser.XMLConfigurationParser;
import org.orson.mybatis.v1.app.configuration.Configuration;
import org.orson.mybatis.v1.app.configuration.SqlSessionFactory;

import java.io.InputStream;

/**
 * Responsible for SQLSessionFactory creation.
 * Created by orson on 2018/9/26.
 */
public class SqlSessionFactoryBuilder {


    /**
     * this configuration parser instance
     */
    XMLConfigurationParser configurationParser;

    /**
     * consturctor
     */
    public SqlSessionFactoryBuilder(XMLConfigurationParser configurationParser) {
        this.configurationParser = configurationParser;
    }


    /**
     * @param resource
     * @return
     */
    public SqlSessionFactory build(InputStream resource) {
        //new configuration
        Configuration configuration = new Configuration();

        //parse xml
        configurationParser.parse(configuration, resource);

        return new SqlSessionFactory(configuration);

    }

}
