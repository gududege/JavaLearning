package com.model;

import com.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息表：
 * 表定义<ol>
 * <li>id int unsigned not NULL auto_increment</li>
 * <li>UUID varchar(10) not NULL primary key</li>
 * <li>name varchar(10) not NULL</li>
 * <li>password varchar(20) not null default 0</li>
 * <li>phone tinyint unsigned not NULL</li>
 * <li>user_create_date timestamp not NULL default current_timestamp()</li>
 * <li>user_modify_date timestamp not NULL default 0 on update current_timestamp()</li>
 * <li>key(id)</li>
 * </ol>
 *
 * @author Liu
 * @// TODO: 2017/2/19   1、更改timestamp更新逻辑，改为赋值 2、测试每个功能块
 */
public class User_Table implements Table {

    private static Connection connection;

    private static String sql;

    private static final String tablename = "user";

    private final String[] columns = {"UUID", "name", "phone", "user_create_date", "user_modify_date"};

    @Override
    public boolean createTable() throws SQLException {
        connection = DB.getConnection();
        sql = new StringBuffer().append("create table if not exists " + tablename + "(")
                .append(" id int unsigned not NULL auto_increment,")
                .append(" UUID varchar(10) not NULL primary key,")
                .append(" name varchar(20) not NULL unique,")
                .append(" password varchar(20) not null default 0,")
                .append(" phone bigint unsigned not NULL default 0 unique,")
                .append(" user_create_date timestamp not NULL default current_timestamp(), ")
                .append(" user_modify_date timestamp not NULL default current_timestamp() on update current_timestamp(), ")
                .append(" key(id))")
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        pstm.close();
        connection.close();
        boolean bool = false;
        if (Util.istableexist(tablename)) {
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean dropTable() throws SQLException {
        connection = DB.getConnection();
        sql = "DROP TABLE IF EXISTS " + tablename;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        pstm.close();
        connection.close();
        boolean bool = true;
        if (Util.istableexist(tablename)) {
            bool = false;
        }
        return bool;
    }

    @Override
    public boolean add(Object object) throws SQLException {
        User user = (User) object;
        sql = new StringBuffer().append("insert into " + tablename)
                .append(" (UUID,name,password,phone)")
                .append(" values (?,?,?,?)")
                .toString();
        connection = DB.getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int i = 1;
        pstm.setString(i++, user.getUuid());
        pstm.setString(i++, user.getName());
        pstm.setString(i++, user.getPassword());
        pstm.setLong(i++, user.getPhone());
        pstm.execute();
        boolean bool = false;
        sql = null;
        sql = "SELECT * FROM " + tablename + " where UUId = '" + user.getUuid() + "'";
        pstm = connection.prepareStatement(sql);
        if (pstm.execute()) {
            bool = true;
        }
        sql = null;
        pstm.close();
        connection.close();
        return bool;
    }

    @Override
    public void update(Object object) throws SQLException {
        User user = (User) object;
        connection = DB.getConnection();
        sql = new StringBuffer().append("update " + tablename)
                .append(" set name=?,password=?,phone=?")
                .append(" where UUID= " + "'" + user.getUuid() + "'")
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int i = 1;
        pstm.setString(i++, user.getName());
        pstm.setString(i++, user.getPassword());
        pstm.setLong(i++, user.getPhone());
        pstm.execute();
        sql = null;
        pstm.close();
        connection.close();
    }

    @Override
    public Object[] queryField(String field, String condition, String relation, String value) throws SQLException {
        Object[] objects = new Object[10];
        connection = DB.getConnection();
        sql = "SELECT " + field + " FROM " + tablename + " WHERE " + condition + relation + value;
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
            if (count == 10) {
                break;
            }
            objects[count] = rs.getObject(field);
        }
        sql = null;
        pstm.close();
        connection.close();
        return objects;
    }

    @Override
    public boolean queryValueIsExists(String field, String value) throws SQLException {
        boolean bool = false;
        connection = DB.getConnection();
        sql = "SELECT " + field + " FROM " + tablename + " WHERE " + field + " = " + value;
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            bool = true;
        }
        sql = null;
        pstm.close();
        connection.close();
        return bool;
    }

    @Override
    public User query(String condition, String relation, String value) throws SQLException {
        Map<String, String> map = new HashMap<>();
        map.put("condition", condition);
        map.put("relation", relation);
        map.put("value", value);
        return query(map);
    }

    @Override
    public User query(Map<String, String> map) throws SQLException {
        User user = new User();
        Calendar cal = Calendar.getInstance();
        connection = DB.getConnection();
        sql = new StringBuffer().append("select * from " + tablename + " where ")
                .append(map.get("condition") + map.get("relation") + map.get("value"))
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String uuid = rs.getString("UUID");
            user.setUuid(uuid);
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getLong("phone"));
            cal.setTimeInMillis(rs.getTimestamp("user_create_date").getTime());
            user.setUser_create_date(cal);
            cal.setTimeInMillis(rs.getTimestamp("user_modify_date").getTime());
            user.setUser_modify_date(cal);
        }else {
            user = null;
        }
        cal = null;
        sql = null;
        rs.close();
        pstm.close();
        connection.close();
        return user;
    }

    @Override
    public ArrayList<User> getObjects(String condition, String relation, String value) throws SQLException {
        Map<String, String> map = new HashMap<>();
        map.put("condition", condition);
        map.put("relation", relation);
        map.put("value", value);
        return getObjects(map);
    }

    @Override
    public ArrayList<User> getObjects(Map<String, String> map) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        User user = null;
        Calendar cal = Calendar.getInstance();
        connection = DB.getConnection();
        sql = new StringBuffer().append("select * from " + tablename + " where ")
                .append(map.get("condition") + map.get("relation") + map.get("value"))
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setUuid(rs.getString("UUID"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getLong("phone"));
            cal.setTimeInMillis(rs.getTimestamp("user_create_date").getTime());
            user.setUser_create_date(cal);
            cal.setTimeInMillis(rs.getTimestamp("user_modify_date").getTime());
            user.setUser_modify_date(cal);
            users.add(user);
            user = null;
        }
        cal = null;
        sql = null;
        rs.close();
        pstm.close();
        connection.close();
        return users;
    }
}
