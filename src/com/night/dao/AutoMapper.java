package com.night.dao;

import com.night.model.Auto;

import java.util.List;

public interface AutoMapper extends Mapper{
    
    Auto selectOneById(int id);

    Auto selectOneByName(String name);

    List<Auto> selectList();

    void saveAuto(Auto Auto);

    void addAuto(Auto Auto);

    void deleteAuto(Auto auto);

    void createTable();

}
