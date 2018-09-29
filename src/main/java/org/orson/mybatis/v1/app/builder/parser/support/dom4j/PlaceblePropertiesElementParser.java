package org.orson.mybatis.v1.app.builder.parser.support.dom4j;

import org.dom4j.Element;
import org.orson.mybatis.v1.app.builder.parser.reslover.PropertiesResolver;
import org.orson.mybatis.v1.app.configuration.Configuration;

/**
 * Created by orson on 2018/9/29.
 */
public class PlaceblePropertiesElementParser extends PropertiesElementParser {

    @Override
    public void parse(Configuration configuration, Element element) {

        //before

        super.parse(configuration, element);

        //after resolve the properties
        new PropertiesResolver(configuration.getProperties()).reslove();
    }

}
