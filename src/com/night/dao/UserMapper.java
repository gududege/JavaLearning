package com.night.dao;

import com.night.model.User;

import java.util.List;

public interface UserMapper{

    User getOneById(int id);

    User getOneByName(String name);

    User getOneByUUID(String UUID);

    List<User> getList();

    void saveUser(User user);

    void addUser(User user);

    void deleteUser(User user);

    void createTable();

}

