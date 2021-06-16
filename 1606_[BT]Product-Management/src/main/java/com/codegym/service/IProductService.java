package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll() throws Exception;
    Product create(Product product) throws Exception;
    Product findByID(int id) throws Exception;
    Product update(int id,Product product) throws Exception;
    void delete(int id) throws Exception;
    Product findByName(String name) throws Exception;

}
