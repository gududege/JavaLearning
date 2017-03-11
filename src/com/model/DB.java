package com.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.cfg.C3P0Config;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Liu
 */
public class DB {

    /**
     * 获取root用户的连接
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getRootConnection() throws SQLException {
        return getConnection("root");
    }

    /**
     * 获取customer连接
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getCustomerConnection() throws SQLException {
        return getConnection("customer");
    }

    /**
     * 获取连接
     *
     * @param str 获取连接的类型
     * @return Connection
     * @throws SQLException
     */
    private static Connection getConnection(String str) throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource(str);
        Connection rootconn = cpds.getConnection();
        return rootconn;
    }
}
