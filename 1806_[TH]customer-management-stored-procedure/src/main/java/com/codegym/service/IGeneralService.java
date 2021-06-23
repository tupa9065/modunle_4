package com.codegym.service;

import com.codegym.exception.DuplicateEmailException;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll() throws Exception;

    Optional<T> findById(Long id);

    void save(T t) throws DuplicateEmailException;

    void remove(Long id);
}
