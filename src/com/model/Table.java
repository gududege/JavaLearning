package com.model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 表接口
 *
 * @author Liu
 */
public interface Table {

    /**
     * 创建表
     *
     * @return true 创建成功;flase 创建失败
     * @throws SQLException
     */
    public boolean createTable() throws SQLException;

    /**
     * 删除表
     *
     * @return true 删除成功;flase 删除失败
     * @throws SQLException
     */
    public boolean dropTable() throws SQLException;

    /**
     * 表插入对象
     *
     * @param object
     * @return
     * @throws SQLException
     */
    public boolean add(Object object) throws SQLException;

    /**
     * 更新表对象
     *
     * @param object
     * @throws SQLException
     */
    public void update(Object object) throws SQLException;

    /**
     * 查询表内该字段内是否有该值
     *
     * @param field-字段名称,value-字段值
     * @return
     * @throws SQLException
     */
    public boolean queryValueIsExists(String field, String value) throws SQLException;

    /**
     * 查询表内某字段内符合查询条件的值,最多返回10条
     *
     * @param field     查询的字段
     * @param condition 查询条件
     * @param relation  查询操作符
     * @param value     查询值
     * @return 返回符合条件的值
     * @throws SQLException
     */
    public Object[] queryField(String field, String condition, String relation, String value) throws SQLException;

    /**
     * 根据id或UUID查询单个对象
     *
     * @param condition 查询条件
     * @param relation  查询操作符
     * @param value     查询值
     * @return instance
     * @throws SQLException
     */
    public Object query(String condition, String relation, String value) throws SQLException;

    /**
     * 自定义查询条件查询单个对象(有多个符合条件对象只返回第一个)
     *
     * @param map condition-查询条件，relation-操作符，value-查询值
     * @return instance实体类
     * @throws SQLException
     */
    public Object query(Map<String, String> map) throws SQLException;

    /**
     * 自定义查询条件查询多个对象
     *
     * @param condition 查询条件
     * @param relation  查询操作符
     * @param value     查询值
     * @return ArrayList
     * @throws SQLException
     */
    public ArrayList getObjects(String condition, String relation, String value) throws SQLException;

    /**
     * 自定义查询条件查询多个对象
     *
     * @param map condition-查询条件，relation-操作符，value-查询值
     * @return ArrayList
     * @throws SQLException
     */
    public ArrayList getObjects(Map<String, String> map) throws SQLException;
}
