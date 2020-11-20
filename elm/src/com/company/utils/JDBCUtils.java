package com.company.utils;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    static {
        try {
            Properties pro=new Properties();
            InputStream in=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(in);
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception{
        return ds.getConnection();
    }
    public static void close(ResultSet resultSet, Statement statement,Connection connection) throws SQLException {
        if(resultSet!=null){
            resultSet.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    }
    public static void close(Statement statement,Connection connection) throws SQLException {
        close(null,statement,connection);
    }
}
