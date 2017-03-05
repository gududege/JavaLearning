package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Liu
 */
public class DB {
	
	private static final String dbname = "auto_order_system";
	
	static final String sqlServer = "localhost";
	
	static final String root = "root";
	
	static final String rootpassword = "1397";

	//jdbc连接使用ssl
	static final String databaseUrl = "jdbc:mysql://" + sqlServer + ":3306/" + dbname
			+ "?user=" + root + "&password=" + rootpassword + "&useSSL=true&createDatabaseIfNotExist=true";
	
	static Connection rootconn;
	
	static String sql;
	
	/**
	 * 获取root用户的连接
	 * @return rootconn
	 */
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		rootconn = DriverManager.getConnection(databaseUrl);
		return rootconn;
	}
}
