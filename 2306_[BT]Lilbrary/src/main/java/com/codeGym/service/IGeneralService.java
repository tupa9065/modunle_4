package com.codeGym.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IGeneralService <T>{
    Page<T> findAll() ;

    Optional<T> findById(Long id);

    void save(T t) ;

    void remove(Long id);
}
