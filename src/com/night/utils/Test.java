package com.night.utils;

import com.night.dao.UserMapper;
import com.night.dao.dbDao;
import com.night.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class Test {

    public static void main(String[] args) throws Exception {
        SqlSessionFactory sqlSessionFactory = com.night.dao.dbDao.getSqlSeesionFactory(dbDao.Environment_Id.developer);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.createTable();
        try {
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
