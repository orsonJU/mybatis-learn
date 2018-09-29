package org.orson.mybatis.v1.app.builder.parser.support.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.orson.mybatis.v1.app.builder.parser.XMLConfigurationParser;
import org.orson.mybatis.v1.app.configuration.Configuration;
import org.orson.mybatis.v1.app.xml.XMLTag;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by orson on 2018/9/27.
 */
public class Dom4jConfigurationParser implements XMLConfigurationParser {


    static final Map<String, ElementParser> parserRegistry = new HashMap<>();

    /**
     * initialized parser registry
     */
    static {
        parserRegistry.put(XMLTag.PROPERTIES.nodeName(), new PropertiesElementParser());

    }

    @Override
    public void parse(Configuration configuration, InputStream resource) {
        //create SAX reader and read xml and transform into document
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(resource);

            //get root element
            Element rootElement = document.getRootElement();


            List<Element> elements = rootElement.elements();
            // parser element
            for(Element element : elements) {

                if(parserRegistry.containsKey(element.getName())) {
                    parserRegistry.get(element.getName()).parse(configuration, element);
                }


            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
