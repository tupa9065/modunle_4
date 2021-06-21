package com.codegym;

import java.util.List;

public interface IGeneral <T>{
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void delete(T t);
}
