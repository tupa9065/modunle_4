package com.codeGym.service;

import com.codeGym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService <T>{
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(Long id);
    T save(T t);
    void delete(T t);
}
