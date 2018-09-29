package org.orson.mybatis.v1.app.builder.parser.support.dom4j;

import org.dom4j.Element;
import org.orson.mybatis.v1.app.configuration.Configuration;



/**
 * Created by orson on 2018/9/27.
 */
public interface ElementParser {


    /**
     * parse a node, and set result to class Configuration
     * @param configuration
     * @param element
     */
    public void parse(Configuration configuration, Element element);
}
