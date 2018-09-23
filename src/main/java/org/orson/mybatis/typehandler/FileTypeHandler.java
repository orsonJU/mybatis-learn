package org.orson.mybatis.typehandler;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by orson on 2018/9/22.
 */
public class FileTypeHandler extends BaseTypeHandler<File> {



    public void setNonNullParameter(PreparedStatement ps, int i, File parameter, JdbcType jdbcType) throws SQLException {

        boolean readable = Files.isReadable(Paths.get(parameter.getPath()));
        Reader reader = null;
        if(readable) {
            try {
                reader = new FileReader(parameter);
            } catch (FileNotFoundException e) {
                //log error
                throw new RuntimeException("Can't locate file: " + parameter);
            }
        }else {
            throw new RuntimeException("File " + parameter + " is not accessible.");
        }

        ps.setClob(i, reader);
    }

    public File getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Clob clob = rs.getClob(columnName);
        File file = null;
        try {
            //write file to a temporary folder
            Path path = Files.write(Paths.get("/temp"), clob.getSubString(1, (int) clob.length()).getBytes());
            file = path.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public File getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Clob clob = rs.getClob(columnIndex);
        File file = null;
        try {
            //write file to a temporary folder
            Path path = Files.write(Paths.get("/temp"), clob.getSubString(1, (int) clob.length()).getBytes());
            file = path.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public File getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Clob clob = cs.getClob(columnIndex);
        File file = null;
        try {
            //write file to a temporary folder
            Path path = Files.write(Paths.get("/temp"), clob.getSubString(1, (int) clob.length()).getBytes());
            file = path.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
