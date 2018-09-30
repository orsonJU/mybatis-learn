package org.orson.mybatis.v1.app.xml;

/**
 * Created by orson on 2018/9/26.
 */
public enum XMLTag {


    CONFIGURATION("configuration"), PROPERTIES("properties"), MAPPERS("mappers");


    /**
     * represent the element tag name
     */
    private String nodeName;

    XMLTag(String nodeName) {
        this.nodeName = nodeName;
    }

    public String nodeName() {
        return this.nodeName;
    }
}
