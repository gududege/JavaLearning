package com.night.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class dbDao {

    public enum Environment_Id {
        developer,
        user
    }

    private static SqlSessionFactory adminSqlSeesionFactory = getSqlSessionFactory(Environment_Id.developer);

    private static SqlSessionFactory userSqlSeesionFactory = getSqlSessionFactory(Environment_Id.user);

    public static SqlSessionFactory getSqlSeesionFactory(Environment_Id environmentId){
        if (environmentId.equals(Environment_Id.developer)){
            return adminSqlSeesionFactory;
        }else if (environmentId.equals(Environment_Id.user)){
            return userSqlSeesionFactory;
        }else {
            return null;
        }
    }

    private static SqlSessionFactory getSqlSessionFactory(Environment_Id environmentId) {
        String xmlpath = dbDao.class.getResource("/com/night/conf/mybatis-config.xml").getFile();
        Reader reader = null;
        try {
            reader = new FileReader(xmlpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, environmentId.toString());
        return sqlSessionFactory;
    }
}
