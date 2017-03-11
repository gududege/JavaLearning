package com.model;

import com.utils.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 汽车信息表：
 * 表定义（id字段不可更改）<ol>
 * <li>id smallint unsigned not null primary key</li>
 * <li>name varchar(10) not NULL</li>
 * <li>type varchar(10) not NULL</li>
 * <li>quality smallint unsigned not NULL</li>
 * <li>weight smallint unsigned not NULL</li>
 * <li>passengernumber tinyint unsigned  not NULL</li>
 * <li>dayprice smallint unsigned not NULL</li>
 * </ol>
 *
 * @author Liu
 * @// TODO: 2017/2/19 1、测试每个功能块
 */
public class Auto_Table implements Table {

    private static Connection connection;

    private static String sql;

    private static String tablename = "";

    private final String[] columns = {"id", "name", "type", "quality", "weight", "passengernumber", "dayprice"};

    static {
        Properties properties = new Properties();
        String filepath = "conf/"+Auto_Table.class.getSimpleName()+".properties";
        try {
            InputStream in = new FileInputStream(filepath);
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("缺少"+filepath+"文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablename = properties.getProperty("table_name");
    }

    @Override
    public boolean createTable() throws SQLException {
        connection = DB.getRootConnection();
        sql = new StringBuffer().append("create table if not exists " + tablename + "(")
                .append(" id smallint unsigned not null primary key,")
                .append(" name varchar(10) not NULL,")
                .append(" type varchar(10) not NULL,")
                .append(" quality smallint unsigned not NULL,")
                .append(" weight smallint unsigned not NULL,")
                .append(" passengernumber tinyint unsigned  not NULL, ")
                .append(" dayprice smallint unsigned not NULL);").toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        sql = null;
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
        connection = DB.getRootConnection();
        sql = "drop table if exists " + tablename;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.execute();
        sql = null;
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
        Auto auto = (Auto) object;
        sql = new StringBuffer().append("insert into " + tablename)
                .append(" (id,name,type,quality,weight,passengernumber,dayprice)")
                .append(" values (?,?,?,?,?,?,?)")
                .toString();
        connection = DB.getRootConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int i = 1;
        pstm.setInt(i++, auto.getId());
        pstm.setString(i++, auto.getName());
        pstm.setString(i++, auto.getType());
        pstm.setInt(i++, auto.getQuality());
        pstm.setInt(i++, auto.getWeight());
        pstm.setInt(i++, auto.getPassengernumber());
        pstm.setInt(i++, auto.getDayprice());
        pstm.execute();
        boolean bool = false;
        sql = "SELECT * FROM " + tablename + "where id = " + auto.getId();
        pstm = connection.prepareStatement(sql);
        if (pstm.execute()) {
            bool = true;
        }
        sql = null;
        pstm.close();
        connection.close();
        return bool;
    }

    /**
     * 根据Auto的id定位修改的对象（id字段不可更改）
     *
     * @param object Auto类对象
     * @throws SQLException
     */
    @Override
    public void update(Object object) throws SQLException {
        Auto auto = (Auto) object;
        connection = DB.getRootConnection();
        sql = new StringBuffer().append("update " + tablename)
                .append(" set name=?,type=?,quality=?,")
                .append(" weight=?,passengernumber=?,dayprice=? ")
                .append(" where id= " + auto.getId())
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int i = 1;
        pstm.setString(i++, auto.getName());
        pstm.setString(i++, auto.getType());
        pstm.setInt(i++, auto.getQuality());
        pstm.setInt(i++, auto.getWeight());
        pstm.setInt(i++, auto.getPassengernumber());
        pstm.setInt(i++, auto.getDayprice());
        pstm.execute();
        sql = null;
        pstm.close();
        connection.close();
    }

    @Override
    public Object[] queryField(String field, String condition, String relation, String value) throws SQLException {
        Object[] objects = new Object[10];
        connection = DB.getRootConnection();
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
        connection = DB.getRootConnection();
        sql = "SELECT " + field + " FROM " + tablename + " WHERE " + field + "=" + value;
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
    public Auto query(String condition, String relation, String value) throws SQLException {
        Map<String,String> map = new HashMap<>();
        map.put("condition",condition);
        map.put("relation",relation);
        map.put("value",value);
        return query(map);
    }

    public Auto query(Map<String, String> map) throws SQLException {
        Auto auto = new Auto();
        connection = DB.getRootConnection();
        sql = new StringBuffer().append("select * from " + tablename + " where ")
                .append(map.get("condition") + map.get("relation") + map.get("value"))
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        auto.setId(rs.getInt("id"));
        auto.setName(rs.getString("name"));
        auto.setType(rs.getString("type"));
        auto.setQuality(rs.getInt("quality"));
        auto.setWeight(rs.getInt("weight"));
        auto.setPassengernumber(rs.getInt("passengernumber"));
        auto.setDayprice(rs.getInt("dayprice"));
        sql = null;
        rs.close();
        pstm.close();
        connection.close();
        return auto;
    }

    @Override
    public ArrayList<Auto> getObjects(String condition, String relation, String value) throws SQLException {
        Map<String,String> map = new HashMap<>();
        map.put("condition",condition);
        map.put("relation",relation);
        map.put("value",value);
        return getObjects(map);
    }

    @Override
    public ArrayList<Auto> getObjects(Map<String, String> map) throws SQLException {
        ArrayList<Auto> autos = new ArrayList<>();
        Auto auto = null;
        connection = DB.getRootConnection();
        sql = new StringBuffer().append("select * from " + tablename + " where ")
                .append(map.get("condition") + map.get("relation") + map.get("value"))
                .toString();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            auto = new Auto();
            auto.setId(rs.getInt("id"));
            auto.setName(rs.getString("name"));
            auto.setType(rs.getString("type"));
            auto.setQuality(rs.getInt("quality"));
            auto.setWeight(rs.getInt("weight"));
            auto.setPassengernumber(rs.getInt("passengernumber"));
            auto.setDayprice(rs.getInt("dayprice"));
            autos.add(auto);
            auto = null;
        }
        sql = null;
        rs.close();
        pstm.close();
        connection.close();
        return autos;
    }
}
