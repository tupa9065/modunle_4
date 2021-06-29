package com.codegym.Service;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();
    T save(T t);
    Optional<T> findById(Long id);
    void delete(T t);
}
