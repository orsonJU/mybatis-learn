package org.orson.mybatis.v1.app.session;

/**
 * Created by orson on 2018/9/24.
 */
public interface OrsonSQLSession {

    /**
     * Get target mapper
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz);
}
