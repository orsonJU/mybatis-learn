package org.orson.mybatis.v1.app.builder.parser.support.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.orson.mybatis.v1.app.configuration.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by orson on 2018/9/27.
 */
public class PropertiesElementParser implements ElementParser {
    
    @Override
    public void parse(Configuration configuration, Element element) {
        //initialized an empty properties
        Properties properties = new Properties();

        //get resource attribute
        Attribute resourceAttr = element.attribute("resource");
        String path = resourceAttr.getValue();
        if(path != null && path.trim().length() != 0) {
            InputStream externalResource = this.getClass().getClassLoader().getResourceAsStream(path);
            //load external resource into properties set
            try {
                properties.load(externalResource);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Resource %s not found.", path));
            }
        }

        //handle child properties
        properties.putAll(this.parseCustomizedzProperties(element));

        //put properties into configuration instance
        configuration.setProperties(properties);
    }

    /**
     * parse all child properties
     * @param parent
     * @return
     */
    private Map<String, String> parseCustomizedzProperties(Element parent) {
        if(Objects.isNull(parent)) {
            throw new NullPointerException();
        }

        List<Element> children = parent.elements();
        Map<String, String> customizedProp = new HashMap<>();
        for(Element element : children) {
            Attribute nameAttr = element.attribute("name");
            Attribute valueAttr = element.attribute("value");
            //put customized property
            customizedProp.put(nameAttr.getValue(), valueAttr.getValue());
        }

        return customizedProp;
    }
}
