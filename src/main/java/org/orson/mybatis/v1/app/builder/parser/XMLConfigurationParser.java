package org.orson.mybatis.v1.app.builder.parser;

import org.orson.mybatis.v1.app.configuration.Configuration;

import java.io.InputStream;

/**
 * 解释XML的实现有多种多样，为了以后可以方便迅速的切换其他解释框架，将具体的实现行为交给具体框架技术实现。
 * Created by orson on 2018/9/29.
 */
public interface XMLConfigurationParser {


    /**
     * temporary support input stream at the moment
     * @param configuration
     */
    public void parse(Configuration configuration, InputStream resource);
}
