package org.orson.mybatis.v1.app.session;

import java.util.List;

/**
 * Created by orson on 2018/9/24.
 */
public interface SqlSession {

    /**
     * Get target mapper
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz);


    /**
     * get bound sql by statement id
     * @param statementId
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId);


    /**
     *
     * get result by parameter
     * @param statementId
     * @param arguments
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object[] arguments);


    /**
     * select list by bound sql statement id
     * @param statementId
     * @param <T>
     * @return
     */
    public <T> List<T> selectList(String statementId, Object[] arguments);
}
