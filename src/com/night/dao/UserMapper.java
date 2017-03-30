package com.night.dao;

import com.night.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper{

    User selectOneById(int id);

    User selectOneByName(String name);

    User selectOneByUUID(String UUID);

    List<User> selectList();

    void saveUser(User user);

    void addUser(User user);

    void deleteUser(User user);

    void createTable();

}

