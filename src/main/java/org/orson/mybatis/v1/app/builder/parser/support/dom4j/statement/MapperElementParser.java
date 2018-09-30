package org.orson.mybatis.v1.app.builder.parser.support.dom4j.statement;

import org.apache.ibatis.annotations.Param;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.orson.mybatis.v1.app.builder.parser.support.dom4j.ElementParser;
import org.orson.mybatis.v1.app.configuration.Configuration;
import org.orson.mybatis.v1.app.configuration.MappedStatement;
import org.orson.mybatis.v1.app.enums.ResultType;
import org.orson.mybatis.v1.app.enums.StatementType;
import org.orson.mybatis.v1.app.parameter.ParameterMapping;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * handle mappers element
 * Created by orson on 2018/9/30.
 */
public class MapperElementParser implements ElementParser {


    private static final String PARAMETER_REG = "\\#\\{(\\w*)\\}";


    @Override
    public void parse(Configuration configuration, Element element) {
        //get all child mapper element
        List<Element> mapperElements = element.elements("mapper");

        for(Element mapperElement : mapperElements) {
            Attribute resource = mapperElement.attribute("resource");
            String path = resource.getValue();
            if(path != null && path.trim().length() != 0) {

                //close resource whenever completed or exception occurred
                try (InputStream ras = this.getClass().getClassLoader().getResourceAsStream(path)){
                    //parse and add mapped statement into configuration
                    this.loadAndSolveMapper(configuration, ras);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @param configuration
     * @param ras
     */
    private void loadAndSolveMapper(Configuration configuration, InputStream ras) {

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(ras);


            Element mapper = document.getRootElement();
            Attribute namespaceAttr = mapper.attribute("namespace");
            String namespace = namespaceAttr.getValue();

            Class<?> clzz = Class.forName(namespace);

            List<Element> children = mapper.elements();

            for(Element child : children) {
                String elementName = child.getName();

                //select statement
                if(StatementType.SELECT.value().equals(elementName)) {
                    Attribute idAttr = child.attribute("id");
                    String methodName = idAttr.getValue();
                    String id = namespace.concat(".").concat(methodName);
                    //get method by method name
                    Method targetMethod = this.getMappedMethod(clzz, methodName);

                    //get sql context
                    String rawSql = child.getText().trim();

                    //resolve sql
                    List<ParameterMapping> parameterMappings = this.createParameterMappings(targetMethod, rawSql);

                    MappedStatement mappedStatement = new MappedStatement(id, StatementType.SELECT, ResultType.RESULT_TYPE, parameterMappings, formatSql(rawSql));

                    configuration.addMappedStatement(mappedStatement);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String formatSql(String rawSql) {
        return Pattern.compile(PARAMETER_REG).matcher(rawSql).replaceAll("?");
    }

    /**
     * get target required method
     * @param clzz
     * @param requiredMethodName
     * @return
     */
    private Method getMappedMethod(Class<?> clzz, String requiredMethodName) {
        Method[] declaredMethods = clzz.getDeclaredMethods();

        Method targetMethod = null;
        for(Method method : declaredMethods) {
            if(method.getName().equals(requiredMethodName)) {
                targetMethod = method;
                break;
            }
        }
        return targetMethod;
    }

    private List<ParameterMapping> createParameterMappings(Method method, String rawSql) {
        Matcher matcher = Pattern.compile(PARAMETER_REG).matcher(rawSql);

        //get parameter class types
        Map<String, Class> parameterMap = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for(Parameter parameter : parameters) {

            Param parameterAnnotation = parameter.getAnnotation(Param.class);
            String parameterName = parameterAnnotation.value();
            String name = parameter.getName();
            Class<?> javaType = parameter.getType();
            parameterMap.put(parameterName, javaType);
        }

        //construct parameter mappings
        List<ParameterMapping> parameterMappings = new ArrayList<>();
        int offset = 1;
        while(matcher.find()) {
            String parameterName = matcher.group(1);
            ParameterMapping mapping = new ParameterMapping(offset,parameterName,parameterMap.get(parameterName),null);
            parameterMappings.add(mapping);
        }

        return parameterMappings;
    }
}
