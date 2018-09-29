package org.orson.mybatis.v1.app.builder.parser.reslover;

import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by orson on 2018/9/29.
 */
public class PropertiesResolver {

    private static final String PREFIX = "\\$\\{";

    private static final String SUBFFIX = "\\}";

    private static final String REG_EXPRESSION = "\\$\\{(\\w*)\\}";

    /**
     * Reg expression pattern
     */
    Pattern pattern = Pattern.compile(REG_EXPRESSION);

    Properties properties;

    public PropertiesResolver(Properties properties) {
        this.properties = properties;
    }

    public Properties reslove() {
        Set<Object> keySet = this.properties.keySet();
        for(Object key : keySet) {
            String name = (String)key;
            String newValue = reslovePropertyValue(properties.getProperty(name));
            properties.put(name, newValue);
        }
        return properties;
    }

    /**
     * loop and replace the placeholder
     * @param property
     * @return
     */
    private String reslovePropertyValue(String property) {
        Matcher matcher = pattern.matcher(property);

        String newValue = property;
        while(matcher.find()) {
            String placeholder = matcher.group(1);
            newValue = newValue.replaceAll(PREFIX + placeholder + SUBFFIX, reslovePropertyValue(this.properties.getProperty(placeholder)));
        }

        return newValue;
    }
}
