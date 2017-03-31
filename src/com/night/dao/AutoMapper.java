package com.night.dao;

import com.night.model.Auto;

import java.util.List;

public interface AutoMapper{
    
    Auto getOneById(int id);

    Auto getOneByName(String name);

    List<Auto> getList();

    void saveAuto(Auto Auto);

    void addAuto(Auto Auto);

    void deleteAuto(Auto auto);

    void createTable();

}
