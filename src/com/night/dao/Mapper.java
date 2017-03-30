package com.night.dao;

import com.night.model.Auto;


public interface Mapper {

    <T> void save(Class<T> c);

    void add(Class<?> c);

    void delete(Class<?> c);
}
